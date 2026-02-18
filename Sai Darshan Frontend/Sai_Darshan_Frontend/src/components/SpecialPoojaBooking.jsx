import { useState, useEffect } from 'react';
import { Calendar, IndianRupee, Clock, Info, CheckCircle, Users } from 'lucide-react';
import { useAuth } from '../providers/AuthProvider';
import ApiService from '../services/ApiService';

function SpecialPoojaBooking() {
  const { user } = useAuth();
  const [poojaTypes, setPoojaTypes] = useState([]);
  const [timeSlots, setTimeSlots] = useState([]);
  const [selectedPooja, setSelectedPooja] = useState('');
  const [selectedDate, setSelectedDate] = useState('');
  const [selectedTimeSlot, setSelectedTimeSlot] = useState('');
  const [showSuccess, setShowSuccess] = useState(false);
  const [showDevoteeForm, setShowDevoteeForm] = useState(false);
  const [loading, setLoading] = useState(false);
  const [loadingTypes, setLoadingTypes] = useState(true);
  const [error, setError] = useState('');
  const [acceptedTerms, setAcceptedTerms] = useState(false);
  const [slotCapacities, setSlotCapacities] = useState({});
  const [devoteeDetails, setDevoteeDetails] = useState({
    name: user?.firstName + ' ' + user?.lastName || '',
    age: '',
    gender: '',
    mobile: user?.phoneNumber || '',
    email: user?.email || '',
    sankalp: '',
    gotra: '',
    address: ''
  });

  useEffect(() => {
    loadPoojaTypes();
    loadTimeSlots();
  }, []);

  useEffect(() => {
    if (selectedDate && timeSlots.length > 0) {
      loadSlotCapacities();
    }
  }, [selectedDate, timeSlots]);

  const loadSlotCapacities = async () => {
    try {
      const capacities = {};
      for (const slot of timeSlots) {
        const cap = await ApiService.getPoojaSlotCapacity(selectedDate, slot);
        capacities[slot] = cap;
      }
      setSlotCapacities(capacities);
    } catch (error) {
      console.error('Failed to load slot capacities:', error);
    }
  };

  const loadPoojaTypes = async () => {
    try {
      setLoadingTypes(true);
      const types = await ApiService.getPoojaTypes();
      console.log('Raw pooja types from API:', types);
      // Transform backend data to match frontend structure
      const transformedTypes = types.map(type => {
        console.log('Processing type:', type.displayName, 'Materials:', type.requiredMaterials);
        return {
          id: type.poojaType.toLowerCase().replace(/_/g, ''),
          name: type.displayName,
          price: type.price,
          duration: `${type.durationMinutes} minutes`,
          description: type.description,
          materials: type.requiredMaterials ? type.requiredMaterials.split(',').map(m => m.trim()) : [],
          benefits: type.benefits,
          poojaTypeEnum: type.poojaType
        };
      });
      console.log('Transformed types:', transformedTypes);
      transformedTypes.forEach(t => console.log(`${t.name} materials array:`, t.materials));
      setPoojaTypes(transformedTypes);
    } catch (error) {
      setError('Failed to load pooja types');
    } finally {
      setLoadingTypes(false);
    }
  };

  const loadTimeSlots = async () => {
    try {
      const slots = await ApiService.getPoojaTimeSlots();
      setTimeSlots(slots);
    } catch (error) {
      setError('Failed to load time slots');
    }
  };

  if (!user) {
    return <div>Loading...</div>;
  }

  const handleInitialSubmit = (e) => {
    e.preventDefault();
    if (!selectedPooja || !selectedDate || !selectedTimeSlot) {
      alert('Please select pooja type, date, and time slot');
      return;
    }
    setShowDevoteeForm(true);
  };

  const handleFinalSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    
    // Validation
    if (!devoteeDetails.name || !devoteeDetails.age || !devoteeDetails.gender || 
        !devoteeDetails.mobile || !devoteeDetails.email || !devoteeDetails.sankalp) {
      setError('Please fill all required fields');
      setLoading(false);
      return;
    }

    if (devoteeDetails.mobile.length !== 10 || !/^[0-9]+$/.test(devoteeDetails.mobile)) {
      setError('Please enter a valid 10-digit mobile number');
      setLoading(false);
      return;
    }

    if (parseInt(devoteeDetails.age) < 1 || parseInt(devoteeDetails.age) > 120) {
      setError('Please enter a valid age between 1 and 120');
      setLoading(false);
      return;
    }

    if (!acceptedTerms) {
      setError('Please accept the pooja guidelines to proceed');
      setLoading(false);
      return;
    }
    
    const pooja = poojaTypes.find(p => p.id === selectedPooja);
    if (!pooja) return;

    try {
      const bookingData = {
        poojaType: pooja.poojaTypeEnum,
        date: selectedDate,
        timeSlot: selectedTimeSlot,
        devoteeDetails: {
          name: devoteeDetails.name,
          age: parseInt(devoteeDetails.age),
          gender: devoteeDetails.gender.toUpperCase(),
          mobile: devoteeDetails.mobile,
          email: devoteeDetails.email,
          sankalp: devoteeDetails.sankalp,
          gotra: devoteeDetails.gotra,
          address: devoteeDetails.address
        },
        totalAmount: parseFloat(pooja.price)
      };

      await ApiService.bookPooja(user.id, bookingData);
      setShowSuccess(true);
      
      setTimeout(() => {
        setShowSuccess(false);
        setSelectedPooja('');
        setSelectedDate('');
        setSelectedTimeSlot('');
        setShowDevoteeForm(false);
        setAcceptedTerms(false);
        setDevoteeDetails({
          name: user?.firstName + ' ' + user?.lastName || '',
          age: '',
          gender: '',
          mobile: user?.phoneNumber || '',
          email: user?.email || '',
          sankalp: '',
          gotra: '',
          address: ''
        });
      }, 3000);
    } catch (error) {
      setError(error.message || 'Failed to book pooja');
    } finally {
      setLoading(false);
    }
  };

  const getMinDate = () => {
    const tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    return tomorrow.toISOString().split('T')[0];
  };

  if (showSuccess) {
    return (
      <div className="max-w-2xl mx-auto px-4 py-20 text-center">
        <div className="bg-white rounded-2xl shadow-lg p-12">
          <div className="w-20 h-20 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-6">
            <CheckCircle className="w-12 h-12 text-green-600" />
          </div>
          <h2 className="text-3xl font-bold text-gray-900 mb-3">Pooja Booking Confirmed!</h2>
          <p className="text-orange-600 font-semibold mb-4">Om Sai Ram 🙏</p>
          <div className="bg-orange-50 rounded-xl p-6 mb-6 text-left">
            <h3 className="font-bold text-lg mb-4">Booking Details:</h3>
            <p><strong>Pooja:</strong> {poojaTypes.find(p => p.id === selectedPooja)?.name}</p>
            <p><strong>Date:</strong> {selectedDate}</p>
            <p><strong>Time:</strong> {selectedTimeSlot}</p>
            <p><strong>Devotee:</strong> {devoteeDetails.name}</p>
            <p><strong>Sankalp:</strong> {devoteeDetails.sankalp}</p>
            <p><strong>Mobile:</strong> {devoteeDetails.mobile}</p>
          </div>
          <p className="text-gray-600 mb-4">Pooja booking confirmation sent to your mobile number.</p>
          <p className="text-sm text-gray-500">Please arrive 15 minutes before the scheduled time.</p>
        </div>
      </div>
    );
  }

  if (showDevoteeForm) {
    return (
      <div className="max-w-2xl mx-auto px-4 py-12">
        <div className="bg-white rounded-xl shadow-lg p-8">
          <div className="text-center mb-8">
            <Users className="w-12 h-12 text-orange-600 mx-auto mb-4" />
            <h1 className="text-3xl font-bold text-gray-900 mb-2">Devotee & Sankalp Details</h1>
            <p className="text-orange-600 font-semibold">Complete your pooja booking</p>
          </div>

          {error && (
            <div className="bg-red-50 border-l-4 border-red-500 text-red-700 px-4 py-3 rounded-lg mb-6">
              <div className="flex items-center">
                <span className="mr-2">⚠️</span>
                {error}
              </div>
            </div>
          )}

          <div className="bg-gradient-to-r from-orange-50 to-yellow-50 rounded-xl p-6 mb-6 border-2 border-orange-200">
            <h3 className="text-xl font-bold text-gray-900 mb-3">📋 Booking Summary</h3>
            <div className="space-y-2">
              <div className="flex justify-between">
                <span className="text-gray-600">Pooja Type:</span>
                <span className="font-semibold">{poojaTypes.find(p => p.id === selectedPooja)?.name}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Date:</span>
                <span className="font-semibold">{selectedDate}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Time Slot:</span>
                <span className="font-semibold text-orange-600">{selectedTimeSlot}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Duration:</span>
                <span className="font-semibold">{poojaTypes.find(p => p.id === selectedPooja)?.duration}</span>
              </div>
              <div className="border-t-2 border-orange-300 pt-2 mt-2 flex justify-between items-center">
                <span className="text-lg font-bold text-gray-900">Donation Amount:</span>
                <span className="text-2xl font-bold text-orange-600">₹{poojaTypes.find(p => p.id === selectedPooja)?.price}</span>
              </div>
            </div>
          </div>

          <form onSubmit={handleFinalSubmit} className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">Full Name *</label>
                <input
                  type="text"
                  required
                  value={devoteeDetails.name}
                  onChange={(e) => setDevoteeDetails({...devoteeDetails, name: e.target.value})}
                  className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                  placeholder="Enter full name"
                  pattern="[A-Za-z\s]+"
                  title="Please enter a valid name (letters and spaces only)"
                />
              </div>
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">Age *</label>
                <input
                  type="number"
                  required
                  min="1"
                  max="120"
                  value={devoteeDetails.age}
                  onChange={(e) => setDevoteeDetails({...devoteeDetails, age: e.target.value})}
                  className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                  placeholder="Enter age"
                />
              </div>
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">Gender *</label>
              <div className="flex space-x-4">
                {['Male', 'Female', 'Other'].map((gender) => (
                  <label key={gender} className="flex items-center">
                    <input
                      type="radio"
                      name="gender"
                      value={gender.toLowerCase()}
                      checked={devoteeDetails.gender === gender.toLowerCase()}
                      onChange={(e) => setDevoteeDetails({...devoteeDetails, gender: e.target.value})}
                      className="mr-2"
                      required
                    />
                    <span>{gender}</span>
                  </label>
                ))}
              </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">Mobile Number *</label>
                <input
                  type="tel"
                  required
                  pattern="[0-9]{10}"
                  maxLength="10"
                  value={devoteeDetails.mobile}
                  onChange={(e) => setDevoteeDetails({...devoteeDetails, mobile: e.target.value.replace(/[^0-9]/g, '')})}
                  className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                  placeholder="10-digit mobile number"
                />
              </div>
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">Email Address *</label>
                <input
                  type="email"
                  required
                  value={devoteeDetails.email}
                  onChange={(e) => setDevoteeDetails({...devoteeDetails, email: e.target.value})}
                  className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                  placeholder="your.email@example.com"
                />
              </div>
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">Sankalp (Purpose/Wish) *</label>
              <textarea
                required
                value={devoteeDetails.sankalp}
                onChange={(e) => setDevoteeDetails({...devoteeDetails, sankalp: e.target.value})}
                className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                placeholder="Enter your wish or purpose for this pooja"
                rows="3"
              />
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">Gotra (Optional)</label>
                <input
                  type="text"
                  value={devoteeDetails.gotra}
                  onChange={(e) => setDevoteeDetails({...devoteeDetails, gotra: e.target.value})}
                  className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                  placeholder="Enter your gotra"
                />
              </div>
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">Address (Optional)</label>
                <input
                  type="text"
                  value={devoteeDetails.address}
                  onChange={(e) => setDevoteeDetails({...devoteeDetails, address: e.target.value})}
                  className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-orange-500"
                  placeholder="Enter your address"
                />
              </div>
            </div>

            <div className="bg-blue-50 border-2 border-blue-200 rounded-lg p-4">
              <label className="flex items-start cursor-pointer">
                <input
                  type="checkbox"
                  checked={acceptedTerms}
                  onChange={(e) => setAcceptedTerms(e.target.checked)}
                  className="mt-1 mr-3 w-5 h-5 text-orange-600 focus:ring-orange-500 rounded"
                  required
                />
                <span className="text-sm text-gray-700">
                  I have read and agree to follow all pooja guidelines including arriving 15 minutes early, maintaining sanctity, and following temple protocols during the ceremony.
                </span>
              </label>
            </div>

            <div className="flex gap-4 pt-4">
              <button
                type="button"
                onClick={() => setShowDevoteeForm(false)}
                className="flex-1 bg-gradient-to-r from-blue-600 to-blue-700 text-white py-3 rounded-lg font-semibold hover:from-blue-700 hover:to-blue-800 transition-all"
              >
                Back
              </button>
              <button
                type="submit"
                disabled={loading}
                className="flex-1 bg-gradient-to-r from-orange-600 to-orange-700 text-white py-3 rounded-lg font-semibold hover:from-orange-700 hover:to-orange-800 transition-all disabled:opacity-50 disabled:cursor-not-allowed"
              >
                {loading ? '🔄 Booking...' : 'Confirm Pooja Booking'}
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }

  if (loadingTypes) {
    return (
      <div className="max-w-3xl mx-auto px-4 py-20 text-center">
        <div className="text-2xl text-gray-600">Loading pooja types...</div>
      </div>
    );
  }

  return (
    <div className="max-w-3xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <div className="bg-white rounded-xl shadow-lg p-8">
        <div className="text-center mb-8">
          <div className="text-4xl mb-3">🕉️</div>
          <h1 className="text-3xl font-bold text-gray-900 mb-2">Book Special Pooja</h1>
          <p className="text-orange-600 font-semibold mb-4">Om Sai Ram 🙏</p>
        </div>

        <form onSubmit={handleInitialSubmit} className="space-y-8">
          <div>
            <label className="block text-lg font-semibold text-gray-700 mb-4">
              Select Pooja Type *
            </label>
            <div className="grid grid-cols-1 gap-6">
              {poojaTypes.map((pooja) => (
                <div
                  key={pooja.id}
                  onClick={() => setSelectedPooja(pooja.id)}
                  className={`p-6 border-2 rounded-xl cursor-pointer transition-all hover:shadow-lg ${
                    selectedPooja === pooja.id
                      ? 'border-orange-600 bg-orange-50 shadow-lg'
                      : 'border-gray-300 hover:border-orange-400'
                  }`}
                >
                  <div className="flex justify-between items-start mb-4">
                    <div className="flex-1">
                      <h3 className="font-bold text-xl text-gray-800 mb-2">{pooja.name}</h3>
                      <p className="text-gray-600 mb-3">{pooja.description}</p>
                      <div className="flex items-center text-blue-600 font-medium mb-3">
                        <Clock className="w-4 h-4 mr-2" />
                        {pooja.duration}
                      </div>
                      <p className="text-sm text-green-700 font-medium mb-3">{pooja.benefits}</p>
                    </div>
                    <div className="text-right ml-4">
                      <div className="flex items-center text-2xl font-bold text-orange-600 mb-2">
                        <IndianRupee className="w-6 h-6" />
                        {pooja.price}
                      </div>
                    </div>
                  </div>
                  <div className="border-t border-gray-200 pt-3">
                    <h4 className="font-semibold text-sm text-gray-700 mb-2">Required Materials:</h4>
                    <p className="text-sm text-gray-600">
                      {pooja.materials && pooja.materials.length > 0 ? pooja.materials.join(', ') : 'Materials will be provided by temple'}
                    </p>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label className="block text-lg font-semibold text-gray-700 mb-3">
                <Calendar className="w-5 h-5 inline mr-2" />
                Select Date *
              </label>
              <input
                type="date"
                required
                min={getMinDate()}
                value={selectedDate}
                onChange={(e) => setSelectedDate(e.target.value)}
                className="w-full p-4 border-2 border-gray-300 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 text-lg"
              />
            </div>

            <div>
              <label className="block text-lg font-semibold text-gray-700 mb-3">
                <Clock className="w-5 h-5 inline mr-2" />
                Select Time Slot *
              </label>
              <select
                required
                value={selectedTimeSlot}
                onChange={(e) => setSelectedTimeSlot(e.target.value)}
                className="w-full p-4 border-2 border-gray-300 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 text-lg"
              >
                <option value="">Select time slot</option>
                {timeSlots.map((slot) => {
                  const capacity = slotCapacities[slot];
                  const availableText = capacity ? ` (${capacity.availableCapacity}/${capacity.maxCapacity} available)` : '';
                  return (
                    <option key={slot} value={slot}>
                      {slot}{availableText}
                    </option>
                  );
                })}
              </select>
            </div>
          </div>

          <div className="bg-gradient-to-r from-orange-50 to-yellow-50 p-6 rounded-xl border-2 border-orange-200">
            <div className="flex justify-between items-center">
              <div>
                <span className="text-lg font-semibold text-gray-700">Donation Amount:</span>
                {selectedPooja && (
                  <p className="text-sm text-gray-600 mt-1">
                    {poojaTypes.find(p => p.id === selectedPooja)?.name}
                  </p>
                )}
              </div>
              {selectedPooja && (
                <div className="flex items-center text-3xl font-bold text-orange-600">
                  <IndianRupee className="w-8 h-8" />
                  {poojaTypes.find(p => p.id === selectedPooja)?.price}
                </div>
              )}
            </div>
          </div>

          <button
            type="submit"
            disabled={!selectedPooja || !selectedDate || !selectedTimeSlot}
            className="w-full bg-gradient-to-r from-orange-600 to-orange-700 text-white py-4 rounded-xl hover:from-orange-700 hover:to-orange-800 transition-all font-bold text-lg disabled:opacity-50 disabled:cursor-not-allowed"
          >
            Proceed to Devotee Details
          </button>
        </form>
      </div>
    </div>
  );
}

export default SpecialPoojaBooking;
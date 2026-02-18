import { useState, useEffect } from 'react';
import { useAuth } from '../providers/AuthProvider';
import { useNavigate } from 'react-router';
import ApiService from '../services/ApiService';

function UserProfile() {
  const { user, setUser } = useAuth();
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    dateOfBirth: '',
    gender: '',
    photoIdProof: '',
    photoIdNumber: '',
    phoneNumber: ''
  });
  const [originalIdProof, setOriginalIdProof] = useState('');
  const [originalIdNumber, setOriginalIdNumber] = useState('');
  const [showSuccess, setShowSuccess] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [fetchingData, setFetchingData] = useState(true);

  useEffect(() => {
    const fetchUserDetails = async () => {
      if (!user?.id) return;
      
      try {
        const fullUserData = await ApiService.getUserDetails(user.id);
        setFormData({
          firstName: fullUserData.firstName || '',
          lastName: fullUserData.lastName || '',
          dateOfBirth: fullUserData.dateOfBirth || '',
          gender: fullUserData.gender?.toLowerCase() || '',
          photoIdProof: fullUserData.photoIdProof || '',
          photoIdNumber: fullUserData.photoIdNumber || '',
          phoneNumber: fullUserData.phoneNumber || ''
        });
        setOriginalIdProof(fullUserData.photoIdProof || '');
        setOriginalIdNumber(fullUserData.photoIdNumber || '');
      } catch (error) {
        setError('Failed to load user details');
      } finally {
        setFetchingData(false);
      }
    };

    fetchUserDetails();
  }, [user?.id]);

  const handleIdProofChange = (newIdProof) => {
    if (newIdProof === originalIdProof) {
      // Switching back to original, restore original number
      setFormData({...formData, photoIdProof: newIdProof, photoIdNumber: originalIdNumber});
    } else {
      // Switching to different ID proof, clear number
      setFormData({...formData, photoIdProof: newIdProof, photoIdNumber: ''});
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    
    try {
      const dataToSend = {
        firstName: formData.firstName,
        lastName: formData.lastName,
        dateOfBirth: formData.dateOfBirth,
        gender: formData.gender.toUpperCase(),
        photoIdProof: formData.photoIdProof,
        photoIdNumber: formData.photoIdNumber,
        phoneNumber: formData.phoneNumber
      };
      
      const updatedData = await ApiService.updateUserDetails(user.id, dataToSend);
      
      const updatedUser = { ...user, ...updatedData };
      setUser(updatedUser);
      localStorage.setItem('user', JSON.stringify(updatedUser));
      
      setShowSuccess(true);
      setTimeout(() => setShowSuccess(false), 3000);
    } catch (error) {
      setError(error.message || 'Failed to update profile');
    } finally {
      setLoading(false);
    }
  };

  if (!user) {
    return <div className="min-h-screen flex items-center justify-center">Loading...</div>;
  }

  if (fetchingData) {
    return <div className="min-h-screen flex items-center justify-center">Loading profile...</div>;
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-orange-50 to-orange-100 p-4">
      <div className="max-w-2xl mx-auto">
        <div className="flex items-center justify-between mb-8">
          <div>
            <h1 className="text-3xl font-bold text-gray-800">👤 My Profile</h1>
            <p className="text-gray-600 mt-2">Update your personal information</p>
          </div>
          <button
            onClick={() => navigate('/user/dashboard')}
            className="bg-gray-500 text-white px-6 py-2 rounded-lg hover:bg-gray-600 transition-all"
          >
            Back
          </button>
        </div>

        {showSuccess && (
          <div className="bg-green-50 border-l-4 border-green-500 text-green-700 px-4 py-3 rounded-lg mb-6">
            <div className="flex items-center">
              <span className="mr-2">✅</span>
              Profile updated successfully!
            </div>
          </div>
        )}

        {error && (
          <div className="bg-red-50 border-l-4 border-red-500 text-red-700 px-4 py-3 rounded-lg mb-6">
            <div className="flex items-center">
              <span className="mr-2">⚠️</span>
              {error}
            </div>
          </div>
        )}

        <div className="bg-white rounded-2xl shadow-xl p-8">
          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  First Name *
                </label>
                <input
                  type="text"
                  required
                  value={formData.firstName}
                  onChange={(e) => setFormData({...formData, firstName: e.target.value})}
                  className="w-full p-3 border-2 border-orange-200 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all"
                />
              </div>
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  Last Name *
                </label>
                <input
                  type="text"
                  required
                  value={formData.lastName}
                  onChange={(e) => setFormData({...formData, lastName: e.target.value})}
                  className="w-full p-3 border-2 border-orange-200 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all"
                />
              </div>
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">
                Email Address (Read Only)
              </label>
              <input
                type="email"
                value={user.email}
                readOnly
                className="w-full p-3 border-2 border-gray-300 rounded-xl bg-gray-100 text-gray-600 cursor-not-allowed"
              />
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  Date of Birth *
                </label>
                <input
                  type="date"
                  required
                  value={formData.dateOfBirth}
                  onChange={(e) => setFormData({...formData, dateOfBirth: e.target.value})}
                  className="w-full p-3 border-2 border-orange-200 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all"
                />
              </div>
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  Phone Number *
                </label>
                <input
                  type="tel"
                  required
                  pattern="[0-9]{10}"
                  maxLength="10"
                  value={formData.phoneNumber}
                  onChange={(e) => setFormData({...formData, phoneNumber: e.target.value.replace(/[^0-9]/g, '')})}
                  className="w-full p-3 border-2 border-orange-200 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all"
                />
              </div>
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-3">
                Gender *
              </label>
              <div className="flex space-x-6">
                {['male', 'female', 'other'].map((gender) => (
                  <label key={gender} className="flex items-center">
                    <input
                      type="radio"
                      name="gender"
                      value={gender}
                      checked={formData.gender === gender}
                      onChange={(e) => setFormData({...formData, gender: e.target.value})}
                      className="mr-2 text-orange-600 focus:ring-orange-500"
                      required
                    />
                    <span className="capitalize">{gender}</span>
                  </label>
                ))}
              </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  Photo ID Proof *
                </label>
                <select
                  required
                  value={formData.photoIdProof}
                  onChange={(e) => handleIdProofChange(e.target.value)}
                  className="w-full p-3 border-2 border-orange-200 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all"
                >
                  <option value="">Select ID Proof</option>
                  <option value="AADHAAR_CARD">Aadhaar Card</option>
                  <option value="PAN_CARD">PAN Card</option>
                  <option value="PASSPORT">Passport</option>
                  <option value="DRIVING_LICENSE">Driving License</option>
                  <option value="VOTER_ID">Voter ID</option>
                </select>
              </div>
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  ID Number *
                </label>
                <input
                  type="text"
                  required
                  value={formData.photoIdNumber}
                  onChange={(e) => setFormData({...formData, photoIdNumber: e.target.value.toUpperCase()})}
                  placeholder="Enter ID number"
                  className="w-full p-3 border-2 border-orange-200 rounded-xl focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all uppercase"
                />
              </div>
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full bg-gradient-to-r from-orange-600 to-orange-700 text-white py-4 rounded-xl hover:from-orange-700 hover:to-orange-800 transition-all font-bold text-lg disabled:opacity-50 disabled:cursor-not-allowed"
            >
              {loading ? 'Updating...' : 'Update Profile'}
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default UserProfile;
# Complete API Testing Guide

## Step 1: Test Login API

### Backend Check
```sql
-- Check user exists with correct role
SELECT id, email, role, password FROM users WHERE email = 'admin@shirdi.com';
-- Should show: role = ADMIN, password = BCrypt hash starting with $2a$
```

### Test Login
Open browser console and run:
```javascript
fetch('http://localhost:8080/user/login', {
  method: 'POST',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({email: 'admin@shirdi.com', password: 'password123'})
})
.then(r => r.json())
.then(d => {
  console.log('Login Response:', d);
  console.log('Role:', d.role);
  console.log('Token:', d.token);
  localStorage.setItem('token', d.token);
  localStorage.setItem('user', JSON.stringify(d));
});
```

**Expected Result:**
- Status: 200 OK
- Response: `{id, firstName, lastName, email, role: "ADMIN", token: "eyJ...", message: "Successfull login"}`
- Role should be "ADMIN" (uppercase)

---

## Step 2: Test Admin Darshan Types API

```javascript
fetch('http://localhost:8080/admin/darshan', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('Darshan Types:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Array with 2 items (General Darshan, VIP Darshan)

---

## Step 3: Test Admin Darshan Slots API

```javascript
fetch('http://localhost:8080/admin/darshan/slots', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('Darshan Slots:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Array with 4 items (Morning/Evening, Regular/VIP slots)

---

## Step 4: Test User Darshan Types API (for booking)

```javascript
fetch('http://localhost:8080/darshan/types', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('User Darshan Types:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Array with darshan types for user booking

---

## Step 5: Test Available Slots API

```javascript
fetch('http://localhost:8080/darshan/slots?date=2025-02-20', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('Available Slots:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Array with available slots for the date

---

## Step 6: Test Aarti Types API

```javascript
fetch('http://localhost:8080/user/aarti/types', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('Aarti Types:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Array with aarti types (format: "Name|Time|Price")

---

## Step 7: Test Pooja Types API

```javascript
fetch('http://localhost:8080/api/pooja/types', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('Pooja Types:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Array with pooja types

---

## Step 8: Test Admin Stats API

```javascript
fetch('http://localhost:8080/admin/stats', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('Admin Stats:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Stats object with booking counts

---

## Step 9: Test Admin Users API

```javascript
fetch('http://localhost:8080/admin/users', {
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  }
})
.then(r => r.json())
.then(d => console.log('All Users:', d));
```

**Expected Result:**
- Status: 200 OK
- Response: Array with 2 users (admin@shirdi.com, pranav@shirdi.com)

---

## Common Issues & Solutions

### Issue 1: 401 Unauthorized
**Cause:** Token missing or invalid
**Solution:** 
1. Check: `localStorage.getItem('token')` - should return JWT token
2. Re-login to get fresh token

### Issue 2: 403 Forbidden
**Cause:** Role is not ADMIN or token has wrong role
**Solution:**
1. Check: `JSON.parse(localStorage.getItem('user')).role` - should be "ADMIN" (uppercase)
2. Check database: `SELECT role FROM users WHERE email = 'admin@shirdi.com'` - should be "ADMIN"
3. If role is correct in DB but wrong in localStorage, logout and login again

### Issue 3: Empty Response []
**Cause:** Database tables are empty
**Solution:** Insert sample data (see below)

---

## Database Sample Data

```sql
-- Darshan Types
INSERT INTO darshan_types (name, description, base_price, is_active) VALUES
('General Darshan', 'Free darshan for all', 0, true),
('VIP Darshan', 'Premium darshan', 500, true);

-- Darshan Slots
INSERT INTO darshan_slots (name, start_time, end_time, type, max_capacity, available_slots, price, is_active) VALUES
('Morning General Darshan', '06:00:00', '09:00:00', 'REGULAR', 100, 100, 0, true),
('Morning VIP Darshan', '06:00:00', '09:00:00', 'VIP', 50, 50, 500, true),
('Evening General Darshan', '18:00:00', '21:00:00', 'REGULAR', 100, 100, 0, true),
('Evening VIP Darshan', '18:00:00', '21:00:00', 'VIP', 50, 50, 500, true);

-- Aarti Types
INSERT INTO aarti_types (name, description, timings, price, is_active) VALUES
('Kakad Aarti', 'Morning aarti', '4:30 AM', 200, true),
('Madhyan Aarti', 'Afternoon aarti', '12:00 PM', 200, true),
('Dhoop Aarti', 'Evening aarti', '6:30 PM', 200, true),
('Shej Aarti', 'Night aarti', '10:30 PM', 200, true);

-- Pooja Types
INSERT INTO pooja_types (pooja_code, display_name, description, duration_minutes, price, benefits, required_materials, active) VALUES
('ABHISHEK', 'Sai Abhishek Pooja', 'Sacred abhishek ceremony', 60, 500, 'Spiritual blessings', 'Flowers, fruits', true),
('NAVAGRAHA_POOJA', 'Navagraha Pooja', 'Nine planets worship', 90, 1100, 'Planetary peace', 'Grains, flowers', true),
('SAHASRANAMA_ARCHANA', 'Sahasranama Archana', 'Thousand names chanting', 45, 300, 'Divine grace', 'Flowers, incense', true);
```

---

## Quick Test All APIs Script

Run this in browser console to test all APIs at once:

```javascript
const token = localStorage.getItem('token');
const headers = { 'Authorization': 'Bearer ' + token };

Promise.all([
  fetch('http://localhost:8080/admin/darshan', {headers}).then(r => r.json()),
  fetch('http://localhost:8080/admin/darshan/slots', {headers}).then(r => r.json()),
  fetch('http://localhost:8080/darshan/types', {headers}).then(r => r.json()),
  fetch('http://localhost:8080/user/aarti/types', {headers}).then(r => r.json()),
  fetch('http://localhost:8080/api/pooja/types', {headers}).then(r => r.json()),
  fetch('http://localhost:8080/admin/users', {headers}).then(r => r.json())
]).then(([darshan, slots, userDarshan, aarti, pooja, users]) => {
  console.log('✅ Admin Darshan Types:', darshan.length, 'items');
  console.log('✅ Admin Darshan Slots:', slots.length, 'items');
  console.log('✅ User Darshan Types:', userDarshan.length, 'items');
  console.log('✅ Aarti Types:', aarti.length, 'items');
  console.log('✅ Pooja Types:', pooja.length, 'items');
  console.log('✅ Users:', users.length, 'items');
}).catch(err => console.error('❌ Error:', err));
```

---

## Expected Output

If everything is working correctly:
```
✅ Admin Darshan Types: 2 items
✅ Admin Darshan Slots: 4 items
✅ User Darshan Types: 2 items
✅ Aarti Types: 4 items
✅ Pooja Types: 3 items
✅ Users: 2 items
```

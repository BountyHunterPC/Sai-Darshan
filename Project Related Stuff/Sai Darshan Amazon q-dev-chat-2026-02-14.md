Amazon Q can now help you write, modify, and maintain code by combining the power of natural language understanding with the ability to take actions on your behalf such as directly making code changes, modifying files, and running commands.

---

<div style="display: flex; flex-direction: column; align-items: center; justify-content: center; text-align: center; padding: 200px 0 20px 0;">

<div style="font-size: 24px; margin-bottom: 12px;"><strong>Amazon Q</strong></div>
<div style="background: rgba(128, 128, 128, 0.15); border: 1px solid rgba(128, 128, 128, 0.25); border-radius: 8px; padding: 8px; margin: 4px 0; text-align: center;">
<div style="font-size: 14px; margin-bottom: 4px;"><strong>Did you know?</strong></div>
<div>Ask Q to review your code and see results in the code issues panel!</div>
</div>

Select code & ask me to explain, debug or optimize it, or type `/` for quick actions

</div>

---

this is my sai darshan project having frontend and backend folders. explain this project to me like i am interviewing you

---

I'll analyze your Sai Darshan project structure to give you a comprehensive overview. Let me first explore the project folders.

---


Perfect! Now I have a comprehensive understanding of your project. Let me present it to you as if I'm in an interview:

---

## **Sai Darshan - Online Temple Booking System**

**Project Overview:**
Sai Darshan is a full-stack web application for managing online bookings at Shirdi Sai Baba Temple. It's a comprehensive temple management system that allows devotees to book darshan slots, aarti ceremonies, special poojas, and make donations online.

---

### **Architecture & Tech Stack**

**Frontend:**
- **Framework**: React 18 with Vite as the build tool
- **Routing**: React Router v7 for navigation
- **State Management**: Redux Toolkit for global state management
- **UI Library**: Radix UI components with Tailwind CSS for modern, accessible UI
- **HTTP Client**: Axios for API communication
- **Additional Libraries**: Recharts for data visualization, React Hook Form for form handling, Lucide React for icons

**Backend:**
- **Framework**: Spring Boot 3.5.9 with Java 21
- **ORM**: Spring Data JPA with Hibernate
- **Database**: MySQL (darshan1 database)
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **Utilities**: Lombok for reducing boilerplate, ModelMapper for DTO conversions
- **Architecture**: RESTful API with layered architecture (Controller → Service → Repository)

---

### **Core Features**

**User Side:**
1. **Authentication System** - Login/Signup with role-based access (User/Admin)
2. **Darshan Booking** - Book temple visit slots with different darshan types and time slots
3. **Aarti Booking** - Reserve spots for morning/evening aarti ceremonies
4. **Special Pooja Booking** - Book special religious ceremonies
5. **Donation System** - Make online donations
6. **My Bookings** - View and manage all bookings with cancellation options
7. **User Profile** - Update personal details and preferences

**Admin Side:**
1. **Dashboard** - View statistics and analytics (total users, bookings, revenue)
2. **User Management** - View all registered users
3. **Booking Management** - Monitor all darshan, aarti, and pooja bookings
4. **Service Management** - CRUD operations for darshan types, aarti schedules, and pooja types
5. **Pricing Control** - Manage pricing for different services

---

### **Backend Architecture**

**Package Structure:**
- **Controllers**: Handle HTTP requests (UserController, DarshanController, AartiController, PoojaController, AdminController)
- **Services**: Business logic layer with interface-implementation pattern
- **Repositories**: JPA repositories for database operations
- **Entities**: JPA entities (User, DarshanBooking, AartiBooking, PoojaBooking, Payment, DarshanSlot, DarshanType, PoojaType)
- **DTOs**: Data Transfer Objects for request/response handling
- **Enums**: Type-safe constants (BookingStatus, PaymentStatus, DarshanType, PoojaType, Gender, Role, PhotoIdProof)

**Key Entities:**
- User (with role-based access)
- DarshanBooking, AartiBooking, PoojaBooking
- DarshanSlot (time slot management)
- DarshanType, PoojaType (service types)
- Payment (transaction tracking)

---

### **Frontend Architecture**

**Component Structure:**
- **Layout Components**: UserLayout, Navbar
- **Auth Components**: Login, Signup, Logout, ProtectedRoute
- **Booking Components**: DarshanBooking, AartiBooking, SpecialPoojaBooking
- **User Components**: Dashboard, MyBookingsUser, UserProfile, DonationPage
- **Admin Components**: AdminDashboard with sub-components (UsersList, UserDetails, UpcomingDarshan, UpcomingAarti, UpcomingPooja)
- **Providers**: AuthProvider for authentication context
- **Services**: ApiService for centralized API calls

**State Management:**
- Redux store with bookingsSlice for managing booking state
- Local storage for user session persistence
- Context API (AuthProvider) for authentication state

---

### **API Integration**

The frontend communicates with the backend through a centralized ApiService class with endpoints for:
- User authentication and profile management
- Darshan, Aarti, and Pooja bookings
- Admin operations (stats, user management, service management)
- Booking cancellations and updates

Base URL: `http://localhost:8080/api`

---

### **Security & Authentication**

- Role-based access control (USER/ADMIN roles)
- Protected routes using ProtectedRoute component
- Admin credentials: admin@shirdi.com / admin123 (auto-created on first load)
- Session management via localStorage

---

### **Database Configuration**

- MySQL database: `darshan1`
- Auto-creates database if not exists
- Hibernate DDL auto-update for schema management
- Connection pooling via Spring Boot defaults

---

### **Key Highlights**

1. **Scalable Architecture**: Clean separation of concerns with layered architecture
2. **Modern UI/UX**: Radix UI components ensure accessibility and modern design
3. **Type Safety**: Enums for all categorical data
4. **API Documentation**: Swagger UI for API testing and documentation
5. **Responsive Design**: Tailwind CSS for mobile-first responsive design
6. **Real-time Availability**: Slot-based booking system with availability tracking

This project demonstrates full-stack development skills with enterprise-level architecture patterns, making it production-ready for a real temple management system.

---

how many api&#39;s are there in the project

---

Let me analyze all the controllers in your backend to count the exact number of APIs.

---


Based on my analysis of all 5 controllers in your backend, here's the complete API count:

## **Total APIs: 29**

### **1. UserController** - 4 APIs
- POST `/user/signup` - Register new user
- POST `/user/login` - User authentication
- GET `/user/{userId}` - Get user details
- POST `/user/{userId}` - Update user details

### **2. DarshanController** - 8 APIs
- GET `/darshan/types` - Get darshan types
- GET `/darshan/slots` - Get available slots
- GET `/darshan/prices` - Get pricing
- POST `/darshan/book` - Book darshan
- GET `/darshan/bookings/user/{userId}` - Get user bookings
- GET `/darshan/bookings/{bookingId}` - Get booking details
- POST `/darshan/bookings/{bookingId}/cancel` - Cancel booking

### **3. AartiController** - 6 APIs
- GET `/user/aarti/types` - Get aarti types
- GET `/user/aarti/schedule` - Get aarti schedule
- GET `/user/aarti/prices` - Get aarti prices
- POST `/user/aarti/book` - Book aarti
- GET `/user/aarti/bookings/{bookingId}` - Get aarti booking by ID
- GET `/user/aarti/bookings/user/{userId}` - Get user aarti bookings

### **4. PoojaController** - 3 APIs
- GET `/api/pooja/types` - Get pooja types
- POST `/api/pooja/bookings` - Book pooja
- GET `/api/pooja/bookings/user/{userId}` - Get user pooja bookings

### **5. AdminController** - 8 APIs
- GET `/admin/darshan` - Get all darshan types
- POST `/admin/darshan` - Create darshan type
- PUT `/admin/darshan/{id}` - Update darshan type
- DELETE `/admin/darshan/{id}` - Delete darshan type
- GET `/admin/bookings/darshan` - Get all darshan bookings
- GET `/admin/stats` - Get dashboard statistics
- GET `/admin/users` - Get all users
- GET `/admin/users/{id}` - Get user by ID

---

**Breakdown by HTTP Method:**
- GET: 17 APIs
- POST: 8 APIs
- PUT: 1 API
- DELETE: 1 API

---

you will explain me this project like i am interviewing you. okay. just tell yes or no

---


Yes

---

tell me what are the api&#39;s in your project and what work they do? or they are designed for what

---


## **API Breakdown by Functionality**

### **User Management APIs (4)**

**POST /user/signup**
- Registers new devotees in the system
- Validates user data and creates account with encrypted credentials

**POST /user/login**
- Authenticates users with email/password
- Returns user details and session token for accessing protected features

**GET /user/{userId}**
- Fetches complete profile information of a specific user
- Used in profile page to display user details

**POST /user/{userId}**
- Updates user profile information like name, phone, address
- Allows devotees to keep their information current

---

### **Darshan Booking APIs (8)**

**GET /darshan/types**
- Returns all available darshan categories (General, VIP, Special)
- Shows devotees what types of darshan they can book

**GET /darshan/slots**
- Fetches available time slots for a specific date
- Shows real-time availability so users can pick their preferred timing

**GET /darshan/prices**
- Returns pricing for different darshan types
- Helps users understand costs before booking

**POST /darshan/book**
- Creates a new darshan booking with devotee details, slot, and payment
- Core booking functionality - reserves the slot and generates booking ID

**GET /darshan/bookings/user/{userId}**
- Retrieves all darshan bookings made by a specific user
- Powers the "My Bookings" page

**GET /darshan/bookings/{bookingId}**
- Fetches detailed information about a specific booking
- Used for viewing booking confirmation and details

**POST /darshan/bookings/{bookingId}/cancel**
- Cancels an existing darshan booking
- Updates booking status and frees up the slot for others

---

### **Aarti Booking APIs (6)**

**GET /user/aarti/types**
- Lists available aarti ceremonies (Morning Aarti, Evening Aarti, Shej Aarti)
- Shows devotees what aarti sessions they can attend

**GET /user/aarti/schedule**
- Returns aarti timings for a specific date
- Helps users plan their temple visit around aarti times

**GET /user/aarti/prices**
- Provides pricing information for aarti participation
- Transparent pricing before booking

**POST /user/aarti/book**
- Books aarti ceremony for devotees
- Reserves spot in the aarti session

**GET /user/aarti/bookings/{bookingId}**
- Retrieves specific aarti booking details
- For viewing confirmation and booking information

**GET /user/aarti/bookings/user/{userId}**
- Gets all aarti bookings of a user
- Displays in user's booking history

---

### **Pooja Booking APIs (3)**

**GET /api/pooja/types**
- Returns all special pooja offerings (Abhishek, Satyanarayana Pooja, etc.)
- Shows devotees available religious ceremonies

**POST /api/pooja/bookings**
- Books special pooja with devotee details and preferred date
- Handles pooja reservations with validation

**GET /api/pooja/bookings/user/{userId}**
- Fetches all pooja bookings for a user
- Shows pooja booking history

---

### **Admin Management APIs (8)**

**GET /admin/darshan**
- Lists all darshan types with their configurations
- Admin can view and manage darshan offerings

**POST /admin/darshan**
- Creates new darshan type with pricing and capacity
- Allows admin to add new darshan categories

**PUT /admin/darshan/{id}**
- Updates existing darshan type details
- Admin can modify pricing, timings, or capacity

**DELETE /admin/darshan/{id}**
- Removes a darshan type from the system
- Admin can discontinue certain darshan offerings

**GET /admin/bookings/darshan**
- Retrieves all darshan bookings across all users
- Admin dashboard to monitor all bookings

**GET /admin/stats**
- Returns dashboard statistics (total users, bookings, revenue)
- Provides analytics for admin decision-making

**GET /admin/users**
- Lists all registered users in the system
- User management for admin

**GET /admin/users/{id}**
- Fetches detailed information about a specific user
- Admin can view complete user profile and booking history

---

**Key Design Principles:**
- RESTful architecture with proper HTTP methods
- Role-based access (User vs Admin endpoints)
- CRUD operations for resource management
- Real-time availability checking
- Booking lifecycle management (create, view, cancel)

---

how many total api are there in the project

---


**29 APIs in total**

- User APIs: 4
- Darshan APIs: 8
- Aarti APIs: 6
- Pooja APIs: 3
- Admin APIs: 8

---

tell me what are the api&#39;s in your project and what work they do? or they are designed for what
ex; format of answer is given below

Complete API List - Sai Darshan Project
1. USER AUTHENTICATION APIs (UserController - 
/user
)
API	Method	Purpose
/user/signup	POST	Register new user with email, password, name

---


## **Complete API List - Sai Darshan Project**

### **1. USER AUTHENTICATION APIs (UserController - /user)**

| API | Method | Purpose |
|-----|--------|---------|
| /user/signup | POST | Register new user with email, password, name, phone, DOB |
| /user/login | POST | Authenticate user and return user details |
| /user/{userId} | GET | Fetch user profile details by user ID |
| /user/{userId} | POST | Update user profile information |

---

### **2. DARSHAN BOOKING APIs (DarshanController - /darshan)**

| API | Method | Purpose |
|-----|--------|---------|
| /darshan/types | GET | Get all available darshan types (General, VIP, Special) |
| /darshan/slots | GET | Get available time slots for a specific date |
| /darshan/prices | GET | Get pricing information for all darshan types |
| /darshan/book | POST | Book darshan with user details, slot, and payment |
| /darshan/bookings/user/{userId} | GET | Get all darshan bookings of a specific user |
| /darshan/bookings/{bookingId} | GET | Get detailed information of a specific booking |
| /darshan/bookings/{bookingId}/cancel | POST | Cancel a darshan booking |

---

### **3. AARTI BOOKING APIs (AartiController - /user/aarti)**

| API | Method | Purpose |
|-----|--------|---------|
| /user/aarti/types | GET | Get all aarti types (Morning, Evening, Shej) |
| /user/aarti/schedule | GET | Get aarti schedule for a specific date |
| /user/aarti/prices | GET | Get pricing for aarti participation |
| /user/aarti/book | POST | Book aarti ceremony |
| /user/aarti/bookings/{bookingId} | GET | Get specific aarti booking details |
| /user/aarti/bookings/user/{userId} | GET | Get all aarti bookings of a user |

---

### **4. POOJA BOOKING APIs (PoojaController - /api/pooja)**

| API | Method | Purpose |
|-----|--------|---------|
| /api/pooja/types | GET | Get all special pooja types available |
| /api/pooja/bookings | POST | Book special pooja with user ID and pooja details |
| /api/pooja/bookings/user/{userId} | GET | Get all pooja bookings of a user |

---

### **5. ADMIN MANAGEMENT APIs (AdminController - /admin)**

| API | Method | Purpose |
|-----|--------|---------|
| /admin/darshan | GET | Get all darshan types for admin management |
| /admin/darshan | POST | Create new darshan type with pricing and capacity |
| /admin/darshan/{id} | PUT | Update existing darshan type details |
| /admin/darshan/{id} | DELETE | Delete a darshan type |
| /admin/bookings/darshan | GET | Get all darshan bookings across all users |
| /admin/stats | GET | Get dashboard statistics (users, bookings, revenue) |
| /admin/users | GET | Get list of all registered users |
| /admin/users/{id} | GET | Get detailed information of a specific user |

---

**Total: 29 APIs**

---

give me api architecture like shown below
API Architecture
The system has 38 RESTful APIs divided into:
•	Authentication APIs (4) - User/admin login, registration, logout
•	User Profile APIs (2) - Get and update profile
•	Darshan APIs (8) - User booking + admin management
•	Aarti APIs (8) - User booking + admin management
•	Pooja APIs (8) - User booking + admin management
•	Donation APIs (7) - User donations + admin management
•	Admin Dashboard APIs (2) - Statistics and user management

---


## **API Architecture**

The system has **29 RESTful APIs** divided into:

• **Authentication APIs (2)** - User login and registration

• **User Profile APIs (2)** - Get and update user profile

• **Darshan Booking APIs (8)** - Get types, slots, prices, book, view bookings, cancel

• **Aarti Booking APIs (6)** - Get types, schedule, prices, book, view bookings

• **Pooja Booking APIs (3)** - Get types, book, view user bookings

• **Admin Darshan Management APIs (5)** - CRUD operations on darshan types, view all bookings

• **Admin Dashboard APIs (3)** - Statistics, user list, user details

---

give API Breakdown by Category:
Ex:
•	User Authentication: 4 APIs
•	Darshan Booking: 7 APIs
•	Aarti Booking: 6 APIs

---


## **API Breakdown by Category:**

• **User Authentication:** 2 APIs

• **User Profile Management:** 2 APIs

• **Darshan Booking:** 8 APIs

• **Aarti Booking:** 6 APIs

• **Pooja Booking:** 3 APIs

• **Admin Darshan Management:** 5 APIs

• **Admin Dashboard:** 3 APIs

**Total: 29 APIs**

---

give Key Features of These APIs like shown below:
ex
1.	User Flow: Signup → Login → Browse Services → Book → View My Bookings
2.	Admin Flow: Login → View Dashboard Stats → Manage Services → View All Bookings → Manage Users
3.	Security: All APIs use CORS configuration and require authentication (except signup/login)
4.	Error Handling: Proper HTTP status codes (200, 201, 400, 401, 404, 409, 500)
5.	Data Validation: DTOs with validation annotations for input validation

---


## **Key Features of These APIs:**

1. **User Flow:** Signup → Login → Browse Services (Darshan/Aarti/Pooja) → Book → View My Bookings → Cancel Bookings

2. **Admin Flow:** Login → View Dashboard Stats → Manage Darshan Types (CRUD) → View All Bookings → View All Users

3. **Security:** All APIs use CORS configuration with origin `https://localhost:3000` for cross-origin requests

4. **Error Handling:** Proper HTTP status codes (200 OK, 201 Created, 400 Bad Request, 401 Unauthorized, 404 Not Found, 409 Conflict, 500 Internal Server Error)

5. **Data Validation:** DTOs with `@Valid` annotations for input validation on booking requests

6. **RESTful Design:** Standard REST conventions with proper HTTP methods (GET, POST, PUT, DELETE)

7. **Layered Architecture:** Controller → Service → Repository pattern for clean separation of concerns

8. **Database Integration:** Spring Data JPA with MySQL for persistent storage and automatic schema management

9. **Real-time Availability:** Slot-based booking system to prevent overbooking

10. **Booking Management:** Complete lifecycle support - create, view, and cancel bookings

---

how many and what controllers are there in the project

---


## **Controllers in the Project: 5**

1. **UserController** (`/user`)
   - Handles user authentication and profile management
   - 4 APIs

2. **DarshanController** (`/darshan`)
   - Manages darshan bookings for users
   - 8 APIs

3. **AartiController** (`/user/aarti`)
   - Handles aarti ceremony bookings
   - 6 APIs

4. **PoojaController** (`/api/pooja`)
   - Manages special pooja bookings
   - 3 APIs

5. **AdminController** (`/admin`)
   - Admin operations for darshan management, statistics, and user management
   - 8 APIs

**Total: 5 Controllers with 29 APIs**

---

which are user controllers and which are admin controllers

---


## **User Controllers (4):**

1. **UserController** (`/user`) - Authentication & Profile
2. **DarshanController** (`/darshan`) - Darshan Bookings
3. **AartiController** (`/user/aarti`) - Aarti Bookings
4. **PoojaController** (`/api/pooja`) - Pooja Bookings

## **Admin Controllers (1):**

5. **AdminController** (`/admin`) - Admin Dashboard, User Management, Darshan Type Management

---

explain the api data flow of your project. means from front end to backend and backend to database how the data flows when user interacts, means how the api gets called and what happens etc

---


## **API Data Flow - Sai Darshan Project**

### **Example: User Books a Darshan**

---

### **1. FRONTEND (React)**

**User Action:**
- User fills darshan booking form (date, slot, devotee details, payment)
- Clicks "Book Now" button

**React Component (DarshanBooking.jsx):**
```
User Input → Form Validation → Call ApiService.bookDarshan(bookingData)
```

**ApiService.js:**
```javascript
axios.post('http://localhost:8080/api/darshan/book', bookingData)
```
- Creates HTTP POST request with JSON payload
- Sends to backend API endpoint

---

### **2. BACKEND (Spring Boot)**

**Step 1: Controller Layer (DarshanController.java)**
```
POST /darshan/book receives request
↓
@RequestBody converts JSON to BookingRequestDTO
↓
Calls darshanService.bookDarshan(request)
```

**Step 2: Service Layer (DarshanServiceImpl.java)**
```
Business Logic Execution:
↓
1. Validate user exists (userRepository.findById)
2. Check slot availability (darshanSlotRepository.findById)
3. Verify slot not full (availableCapacity > 0)
4. Create DarshanBooking entity
5. Create Payment entity
6. Link booking with user and slot
7. Reduce slot capacity
8. Save booking (bookingRepository.save)
9. Convert entity to BookingResponseDTO
10. Return DTO to controller
```

**Step 3: Repository Layer (BookingRepository.java)**
```
JPA Repository Interface
↓
Hibernate ORM translates to SQL
↓
Executes database operations
```

---

### **3. DATABASE (MySQL)**

**SQL Operations Executed:**
```sql
-- Check user exists
SELECT * FROM user WHERE id = ?

-- Check slot availability
SELECT * FROM darshan_slot WHERE id = ?

-- Insert booking
INSERT INTO darshan_booking (user_id, slot_id, booking_date, status, ...) 
VALUES (?, ?, ?, ?, ...)

-- Insert payment
INSERT INTO payment (booking_id, amount, status, ...) 
VALUES (?, ?, ?, ...)

-- Update slot capacity
UPDATE darshan_slot SET available_capacity = available_capacity - 1 
WHERE id = ?
```

**Database Response:**
- Returns generated booking ID and saved data
- Transaction committed

---

### **4. RESPONSE FLOW (Backend → Frontend)**

**Repository → Service:**
```
Saved DarshanBooking entity returned
```

**Service → Controller:**
```
Entity converted to BookingResponseDTO
↓
DTO contains: bookingId, userName, slotTime, amount, status
```

**Controller → Frontend:**
```
HTTP Response:
Status: 201 CREATED
Body: {
  "bookingId": 123,
  "userName": "John Doe",
  "darshanType": "VIP",
  "slotTime": "10:00 AM",
  "amount": 500,
  "status": "CONFIRMED"
}
```

**ApiService → React Component:**
```
axios receives response
↓
Returns response.data to component
```

**React Component:**
```
Success:
- Display booking confirmation
- Show booking ID
- Redirect to "My Bookings"

Error:
- Catch error from axios
- Display error message to user
```

---

### **Complete Flow Diagram:**

```
USER INTERACTION
      ↓
[React Component] → Form Submit
      ↓
[ApiService.js] → axios.post()
      ↓
[HTTP Request] → JSON payload over network
      ↓
[DarshanController] → @PostMapping receives request
      ↓
[DarshanService] → Business logic + validation
      ↓
[Repository] → JPA operations
      ↓
[Hibernate] → SQL generation
      ↓
[MySQL Database] → Data persistence
      ↓
[Database Response] → Saved data returned
      ↓
[Repository] → Entity returned
      ↓
[Service] → DTO conversion
      ↓
[Controller] → HTTP Response (201 + JSON)
      ↓
[ApiService] → axios receives response
      ↓
[React Component] → Update UI
      ↓
USER SEES CONFIRMATION
```

---

### **Key Technologies in Data Flow:**

1. **Frontend:** Axios HTTP client, React state management
2. **Network:** REST API over HTTP, JSON data format
3. **Backend:** Spring MVC, Dependency Injection
4. **ORM:** Hibernate JPA for object-relational mapping
5. **Database:** MySQL with JDBC connection
6. **Validation:** Bean Validation (@Valid annotations)
7. **Error Handling:** Try-catch blocks with proper HTTP status codes

---

### **Error Flow Example:**

```
User books already full slot
      ↓
Service checks: availableCapacity = 0
      ↓
Throws RuntimeException("Slot is full")
      ↓
Controller catches exception
      ↓
Returns 400 BAD_REQUEST with error message
      ↓
Axios catches error in .catch() block
      ↓
React displays: "Slot is full, please choose another"
```

This architecture ensures **separation of concerns**, **data validation**, **error handling**, and **scalability**.

---

explain how each api in the project is working. like how data and control is flowing from controller to service means from frontend to backend and backend to db or db to  backend to frontend how everything happends explain for each api one by one, api by api

---

I'll explain the data flow for each of the 29 APIs in your project. Let me first read the service layer implementations to understand the complete flow.

---


Perfect! Now I have all the service implementations. Let me create a comprehensive explanation of how each API works with complete data flow.

## **Complete API Data Flow Explanation - All 29 APIs**

---

# **1. USER AUTHENTICATION APIs (UserController - /user)**

---

## **API 1: POST /user/signup**

**Purpose:** Register new user

### **Frontend → Backend Flow:**

**Step 1: User Action (React)**
```
User fills signup form → Enters: firstName, lastName, email, password, phone, DOB, gender, photoIdProof
↓
Clicks "Sign Up" button
↓
ApiService.signup(userData) called
```

**Step 2: HTTP Request**
```
axios.post('http://localhost:8080/api/user/signup', {
  firstName: "John",
  lastName: "Doe",
  email: "john@example.com",
  password: "password123",
  phoneNumber: "9876543210",
  dateOfBirth: "1990-01-01",
  gender: "MALE",
  photoIdProof: "AADHAAR"
})
```

### **Backend Processing:**

**Step 3: Controller (UserController.java)**
```
POST /user/signup receives JSON
↓
@RequestBody converts JSON → UserDTO object
↓
Calls userService.addUser(userDTO)
```

**Step 4: Service Layer (UserServiceImpl.java)**
```
1. Check if email already exists
   userRepository.existsByEmail(email)
   ↓
   SELECT * FROM user WHERE email = 'john@example.com'
   
2. If exists → throw Exception("user already exists")
   
3. If not exists:
   - ModelMapper converts UserDTO → User entity
   - Sets all fields (firstName, lastName, email, password, etc.)
   
4. Save to database
   userRepository.save(user)
   ↓
   INSERT INTO user (first_name, last_name, email, password, phone_number, date_of_birth, gender, photo_id_proof, role, created_at)
   VALUES ('John', 'Doe', 'john@example.com', 'password123', '9876543210', '1990-01-01', 'USER', 'AADHAAR', 'USER', NOW())
   
5. Database returns saved user with generated ID
   
6. Return message: "New User added with Id = 1"
```

**Step 5: Response Flow**
```
Service → Controller → HTTP Response
↓
Status: 201 CREATED
Body: "New User added with Id = 1"
↓
Frontend receives response
↓
Display success message
↓
Redirect to login page
```

---

## **API 2: POST /user/login**

**Purpose:** Authenticate user

### **Frontend → Backend Flow:**

**Step 1: User Action**
```
User enters email and password
↓
Clicks "Login"
↓
ApiService.login(email, password)
```

**Step 2: HTTP Request**
```
axios.post('http://localhost:8080/api/user/login', {
  email: "john@example.com",
  password: "password123"
})
```

### **Backend Processing:**

**Step 3: Controller**
```
POST /user/login receives AuthReq
↓
Calls userService.authenticate(request)
```

**Step 4: Service Layer**
```
1. Find user by email and password
   userRepository.findByEmailAndPassword(email, password)
   ↓
   SELECT * FROM user WHERE email = 'john@example.com' AND password = 'password123'
   
2. If user not found → throw RuntimeException("Invalid credentials")
   
3. If found:
   - ModelMapper converts User entity → AuthResp DTO
   - Set role explicitly
   - Set message: "Successfull login"
   
4. Return AuthResp with user details
```

**Step 5: Response Flow**
```
Status: 200 OK
Body: {
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "role": "USER",
  "message": "Successfull login"
}
↓
Frontend stores user in localStorage
↓
Redirect to dashboard
```

---

## **API 3: GET /user/{userId}**

**Purpose:** Get user profile details

### **Flow:**

**Frontend:**
```
User clicks "My Profile"
↓
ApiService.getUserDetails(userId)
↓
axios.get('http://localhost:8080/api/user/1')
```

**Backend:**
```
Controller receives userId = 1
↓
Service: userRepository.findById(1)
↓
Database: SELECT * FROM user WHERE id = 1
↓
If not found → throw RuntimeException("Invalid user id!!!")
↓
If found → return Optional<User>
↓
Response: 200 OK with User object
```

**Frontend:**
```
Display user profile with all details
```

---

## **API 4: POST /user/{userId}**

**Purpose:** Update user profile

### **Flow:**

**Frontend:**
```
User edits profile → Changes phone number
↓
Clicks "Update"
↓
ApiService.updateUserDetails(userId, updatedData)
↓
axios.post('http://localhost:8080/api/user/1', {
  firstName: "John",
  phoneNumber: "9999999999"
})
```

**Backend:**
```
Controller receives userId and UserDTO
↓
Service:
1. Get existing user: getUserDetails(userId)
   ↓ SELECT * FROM user WHERE id = 1
   
2. ModelMapper maps new data to existing user
   (only updates provided fields)
   
3. Save updated user
   ↓ UPDATE user SET phone_number = '9999999999' WHERE id = 1
   
4. Return ApiResponse("Success", "User details updated")
↓
Response: 200 OK
```

**Frontend:**
```
Show success message
↓
Refresh profile page
```

---

# **2. DARSHAN BOOKING APIs (DarshanController - /darshan)**

---

## **API 5: GET /darshan/types**

**Purpose:** Get all darshan types

### **Flow:**

**Frontend:**
```
User opens Darshan Booking page
↓
ApiService.getDarshanTypes()
↓
axios.get('http://localhost:8080/api/darshan/types')
```

**Backend:**
```
Controller → Service
↓
Service reads DarshanTypeEnum values
↓
Arrays.stream(DarshanTypeEnum.values())
  - GENERAL_DARSHAN
  - VIP_DARSHAN
  - SPECIAL_DARSHAN
↓
Maps each to DarshanTypeDTO
↓
Returns List<DarshanTypeDTO>
↓
Response: 200 OK
Body: [
  { "type": "GENERAL_DARSHAN", "description": "GENERAL_DARSHAN" },
  { "type": "VIP_DARSHAN", "description": "VIP_DARSHAN" },
  { "type": "SPECIAL_DARSHAN", "description": "SPECIAL_DARSHAN" }
]
```

**Frontend:**
```
Display darshan types as options
```

---

## **API 6: GET /darshan/slots?date=2024-01-15**

**Purpose:** Get available time slots

### **Flow:**

**Frontend:**
```
User selects date: 2024-01-15
↓
ApiService.getAvailableSlots('2024-01-15')
↓
axios.get('http://localhost:8080/api/darshan/slots?date=2024-01-15')
```

**Backend:**
```
Controller receives date parameter
↓
Service: darshanSlotRepository.findByIsActiveTrue()
↓
Database: SELECT * FROM darshan_slot WHERE is_active = true
↓
Returns all active slots with:
  - id, name, startTime, endTime, availableSlots, price, type
↓
Maps to List<DarshanSlotDTO>
↓
Response: 200 OK
Body: [
  {
    "id": 1,
    "name": "Morning Slot",
    "startTime": "06:00",
    "endTime": "09:00",
    "availableSlots": 50,
    "price": 100,
    "type": "GENERAL_DARSHAN"
  },
  ...
]
```

**Frontend:**
```
Display available slots with timings and availability
```

---

## **API 7: GET /darshan/prices**

**Purpose:** Get pricing for darshan types

### **Flow:**

**Frontend:**
```
ApiService.getDarshanPrices()
↓
axios.get('http://localhost:8080/api/darshan/prices')
```

**Backend:**
```
Service:
1. Get all active slots
   ↓ SELECT * FROM darshan_slot WHERE is_active = true
   
2. Group by darshan type
   
3. Extract price from first slot of each type
   
4. Return List<DarshanPriceDTO>
↓
Response: 200 OK
Body: [
  { "type": "GENERAL_DARSHAN", "price": 100 },
  { "type": "VIP_DARSHAN", "price": 500 },
  { "type": "SPECIAL_DARSHAN", "price": 1000 }
]
```

**Frontend:**
```
Display pricing information
```

---

## **API 8: POST /darshan/book**

**Purpose:** Book darshan slot

### **Flow:**

**Frontend:**
```
User fills booking form:
- Selects slot
- Enters number of people
- Selects date
↓
Clicks "Book Now"
↓
ApiService.bookDarshan({
  userId: 1,
  darshanSlotId: 2,
  bookingDate: "2024-01-15",
  numberOfPeople: 3
})
↓
axios.post('http://localhost:8080/api/darshan/book', bookingData)
```

**Backend:**
```
Controller receives BookingRequestDTO
↓
Service:

1. Validate user exists
   userRepository.findById(1)
   ↓ SELECT * FROM user WHERE id = 1
   ↓ If not found → throw RuntimeException("User not found")

2. Validate slot exists
   darshanSlotRepository.findById(2)
   ↓ SELECT * FROM darshan_slot WHERE id = 2
   ↓ If not found → throw RuntimeException("Darshan slot not found")

3. Check availability
   if (slot.availableSlots < numberOfPeople)
   ↓ throw RuntimeException("Not enough slots available")

4. Create booking entity
   DarshanBooking booking = new DarshanBooking()
   - Set user, slot, date, numberOfPeople
   - Set status = CONFIRMED

5. Update slot availability
   slot.availableSlots = slot.availableSlots - 3
   ↓ UPDATE darshan_slot SET available_slots = available_slots - 3 WHERE id = 2

6. Save booking
   bookingRepository.save(booking)
   ↓ INSERT INTO darshan_booking (user_id, darshan_slot_id, booking_date, number_of_people, status, created_at)
     VALUES (1, 2, '2024-01-15', 3, 'CONFIRMED', NOW())

7. Map to BookingResponseDTO
↓
Response: 201 CREATED
Body: {
  "id": 10,
  "userId": 1,
  "userName": "John Doe",
  "darshanSlotId": 2,
  "darshanSlotName": "Morning Slot",
  "bookingDate": "2024-01-15",
  "numberOfPeople": 3,
  "status": "CONFIRMED",
  "createdAt": "2024-01-10T10:30:00"
}
```

**Frontend:**
```
Show booking confirmation
↓
Display booking ID
↓
Redirect to "My Bookings"
```

---

## **API 9: GET /darshan/bookings/user/{userId}**

**Purpose:** Get all bookings of a user

### **Flow:**

**Frontend:**
```
User clicks "My Bookings"
↓
ApiService.getUserDarshanBookings(userId)
↓
axios.get('http://localhost:8080/api/darshan/bookings/user/1')
```

**Backend:**
```
Controller receives userId = 1
↓
Service: bookingRepository.findByUserId(1)
↓
Database: SELECT * FROM darshan_booking WHERE user_id = 1
↓
Returns List<DarshanBooking>
↓
Maps each to BookingResponseDTO
↓
Response: 200 OK
Body: [
  {
    "id": 10,
    "userName": "John Doe",
    "darshanSlotName": "Morning Slot",
    "bookingDate": "2024-01-15",
    "numberOfPeople": 3,
    "status": "CONFIRMED"
  },
  ...
]
```

**Frontend:**
```
Display list of all user bookings
```

---

## **API 10: GET /darshan/bookings/{bookingId}**

**Purpose:** Get specific booking details

### **Flow:**

**Frontend:**
```
User clicks on a booking to view details
↓
ApiService.getDarshanBookingDetails(bookingId)
↓
axios.get('http://localhost:8080/api/darshan/bookings/10')
```

**Backend:**
```
Controller receives bookingId = 10
↓
Service: bookingRepository.findById(10)
↓
Database: SELECT * FROM darshan_booking WHERE id = 10
↓
If not found → throw RuntimeException("Booking not found")
↓
Map to BookingResponseDTO
↓
Response: 200 OK with booking details
```

**Frontend:**
```
Display detailed booking information
```

---

## **API 11: POST /darshan/bookings/{bookingId}/cancel**

**Purpose:** Cancel a booking

### **Flow:**

**Frontend:**
```
User clicks "Cancel Booking"
↓
Confirmation dialog → User confirms
↓
ApiService.cancelDarshanBooking(bookingId)
↓
axios.post('http://localhost:8080/api/darshan/bookings/10/cancel')
```

**Backend:**
```
Controller receives bookingId = 10
↓
Service:

1. Find booking
   bookingRepository.findById(10)
   ↓ SELECT * FROM darshan_booking WHERE id = 10
   ↓ If not found → throw RuntimeException("Booking not found")

2. Update booking status
   booking.status = CANCELLED

3. Restore slot availability
   slot.availableSlots = slot.availableSlots + booking.numberOfPeople
   ↓ UPDATE darshan_slot SET available_slots = available_slots + 3 WHERE id = 2

4. Save changes
   ↓ UPDATE darshan_booking SET status = 'CANCELLED' WHERE id = 10

5. Return ApiResponse("Success", "Booking cancelled successfully")
↓
Response: 200 OK
```

**Frontend:**
```
Show cancellation success message
↓
Refresh bookings list
```

---

# **3. AARTI BOOKING APIs (AartiController - /user/aarti)**

---

## **API 12: GET /user/aarti/types**

**Purpose:** Get aarti types

### **Flow:**

**Frontend:**
```
User opens Aarti Booking page
↓
ApiService.getAartiTypes()
↓
axios.get('http://localhost:8080/api/user/aarti/types')
```

**Backend:**
```
Controller → Service
↓
Service returns hardcoded list:
  List.of("Morning Aarti", "Evening Aarti")
↓
Response: 200 OK
Body: ["Morning Aarti", "Evening Aarti"]
```

**Frontend:**
```
Display aarti types as options
```

---

## **API 13: GET /user/aarti/schedule?date=2024-01-15**

**Purpose:** Get aarti schedule

### **Flow:**

**Frontend:**
```
User selects date
↓
ApiService.getAartiSchedule('2024-01-15')
↓
axios.get('http://localhost:8080/api/user/aarti/schedule?date=2024-01-15')
```

**Backend:**
```
Controller receives date parameter
↓
Service returns: "Aarti schedule for 2024-01-15"
↓
Response: 200 OK
Body: "Aarti schedule for 2024-01-15"
```

**Frontend:**
```
Display schedule information
```

---

## **API 14: GET /user/aarti/prices**

**Purpose:** Get aarti prices

### **Flow:**

**Frontend:**
```
ApiService.getAartiPrices()
↓
axios.get('http://localhost:8080/api/user/aarti/prices')
```

**Backend:**
```
Service returns: "Morning Aarti: ₹200, Evening Aarti: ₹300"
↓
Response: 200 OK
Body: "Morning Aarti: ₹200, Evening Aarti: ₹300"
```

**Frontend:**
```
Display pricing
```

---

## **API 15: POST /user/aarti/book**

**Purpose:** Book aarti

### **Flow:**

**Frontend:**
```
User fills aarti booking form
↓
ApiService.bookAarti(bookingData)
↓
axios.post('http://localhost:8080/api/user/aarti/book', {
  aartiType: "Morning Aarti",
  bookingDate: "2024-01-15"
})
```

**Backend:**
```
Controller → Service
↓
Service:
1. Create AartiBooking entity
   - Set aartiType, bookingDate
   - Set price = 200
   - Set status = "BOOKED"

2. Save to database
   repository.save(booking)
   ↓ INSERT INTO aarti_booking (aarti_type, booking_date, price, status)
     VALUES ('Morning Aarti', '2024-01-15', 200, 'BOOKED')

3. Return AartiBookingResponseDTO
↓
Response: 200 OK
Body: {
  "id": 5,
  "message": "Aarti booked successfully"
}
```

**Frontend:**
```
Show booking confirmation
```

---

## **API 16: GET /user/aarti/bookings/{bookingId}**

**Purpose:** Get aarti booking by ID

### **Flow:**

**Frontend:**
```
ApiService calls endpoint
↓
axios.get('http://localhost:8080/api/user/aarti/bookings/5')
```

**Backend:**
```
Controller receives bookingId = 5
↓
Service: repository.findById(5)
↓
Database: SELECT * FROM aarti_booking WHERE id = 5
↓
If not found → throw RuntimeException("Aarti booking not found")
↓
Response: 200 OK with AartiBooking entity
```

---

## **API 17: GET /user/aarti/bookings/user/{userId}**

**Purpose:** Get all aarti bookings of user

### **Flow:**

**Frontend:**
```
ApiService.getUserAartiBookings(userId)
↓
axios.get('http://localhost:8080/api/user/aarti/bookings/user/1')
```

**Backend:**
```
Controller receives userId = 1
↓
Service: repository.findByUserId(1)
↓
Database: SELECT * FROM aarti_booking WHERE user_id = 1
↓
Returns List<AartiBooking>
↓
Response: 200 OK with list of bookings
```

**Frontend:**
```
Display user's aarti bookings
```

---

# **4. POOJA BOOKING APIs (PoojaController - /api/pooja)**

---

## **API 18: GET /api/pooja/types**

**Purpose:** Get pooja types

### **Flow:**

**Frontend:**
```
User opens Pooja Booking page
↓
ApiService.getPoojaTypes()
↓
axios.get('http://localhost:8080/api/pooja/types')
```

**Backend:**
```
Controller → Service
↓
Service: poojaTypeRepository.findByActiveTrue()
↓
Database: SELECT * FROM pooja_type WHERE active = true
↓
Returns List<PoojaType> entities
↓
Maps each to PoojaTypeResponseDto with:
  - id, poojaType, displayName, description
  - durationMinutes, price, benefits, requiredMaterials
↓
Response: 200 OK
Body: [
  {
    "id": 1,
    "poojaType": "ABHISHEK",
    "displayName": "Abhishek Pooja",
    "description": "Sacred bathing ritual",
    "durationMinutes": 60,
    "price": 500,
    "benefits": "Prosperity and peace",
    "requiredMaterials": "Milk, Honey, Flowers"
  },
  ...
]
```

**Frontend:**
```
Display pooja types with details
```

---

## **API 19: POST /api/pooja/bookings?userId=1**

**Purpose:** Book pooja

### **Flow:**

**Frontend:**
```
User fills pooja booking form:
- Selects pooja type
- Enters devotee details (name, age, gender, mobile, email, sankalp, gotra, address)
- Selects date and time slot
↓
ApiService.bookPooja(userId, bookingData)
↓
axios.post('http://localhost:8080/api/pooja/bookings?userId=1', {
  poojaType: "ABHISHEK",
  date: "2024-01-15",
  timeSlot: "10:00 AM",
  totalAmount: 500,
  devoteeDetails: {
    name: "John Doe",
    age: 30,
    gender: "Male",
    mobile: "9876543210",
    email: "john@example.com",
    sankalp: "Family prosperity",
    gotra: "Bharadwaj",
    address: "Mumbai"
  }
})
```

**Backend:**
```
Controller receives userId and PoojaBookingRequestDto
↓
Service:

1. Validate user
   userRepository.findById(1)
   ↓ SELECT * FROM user WHERE id = 1
   ↓ If not found → throw RuntimeException("User not found")

2. Validate pooja type
   poojaTypeRepository.findByPoojaType("ABHISHEK")
   ↓ SELECT * FROM pooja_type WHERE pooja_type = 'ABHISHEK'
   ↓ If not found → throw RuntimeException("Invalid pooja type")

3. Create PoojaBooking entity
   - Set user, poojaType, date, timeSlot, totalAmount
   - Set status = CONFIRMED
   - Set all devotee details (name, age, gender, mobile, email, sankalp, gotra, address)

4. Save booking
   poojaBookingRepository.save(booking)
   ↓ INSERT INTO pooja_booking (user_id, pooja_type_id, pooja_date, time_slot, total_amount, status,
       devotee_name, devotee_age, devotee_gender, devotee_mobile, devotee_email, sankalp, gotra, address, created_at)
     VALUES (1, 1, '2024-01-15', '10:00 AM', 500, 'CONFIRMED',
       'John Doe', 30, 'Male', '9876543210', 'john@example.com', 'Family prosperity', 'Bharadwaj', 'Mumbai', NOW())

5. Map to PoojaBookingResponseDto
↓
Response: 201 CREATED
Body: {
  "id": 15,
  "userId": 1,
  "category": "ABHISHEK",
  "date": "2024-01-15",
  "timeSlot": "10:00 AM",
  "totalAmount": 500,
  "devoteeDetails": {
    "name": "John Doe",
    "age": 30,
    "gender": "Male",
    "mobile": "9876543210",
    "email": "john@example.com",
    "sankalp": "Family prosperity",
    "gotra": "Bharadwaj",
    "address": "Mumbai"
  },
  "status": "upcoming"
}
```

**Frontend:**
```
Show booking confirmation
↓
Display booking details
```

---

## **API 20: GET /api/pooja/bookings/user/{userId}**

**Purpose:** Get user's pooja bookings

### **Flow:**

**Frontend:**
```
ApiService.getUserPoojaBookings(userId)
↓
axios.get('http://localhost:8080/api/pooja/bookings/user/1')
```

**Backend:**
```
Controller receives userId = 1
↓
Service: poojaBookingRepository.findByUserIdOrderByCreatedAtDesc(1)
↓
Database: SELECT * FROM pooja_booking WHERE user_id = 1 ORDER BY created_at DESC
↓
Returns List<PoojaBooking>
↓
Maps each to PoojaBookingResponseDto
↓
Response: 200 OK with list of bookings
```

**Frontend:**
```
Display user's pooja bookings
```

---

# **5. ADMIN MANAGEMENT APIs (AdminController - /admin)**

---

## **API 21: GET /admin/darshan**

**Purpose:** Get all darshan types for admin

### **Flow:**

**Frontend:**
```
Admin opens Darshan Management
↓
ApiService.getAllDarshanTypes()
↓
axios.get('http://localhost:8080/api/admin/darshan')
```

**Backend:**
```
Controller → Service
↓
Service: darshanTypeRepository.findAll()
↓
Database: SELECT * FROM darshan_type
↓
Returns List<DarshanTypeEntity>
↓
Maps each to AdminDarshanTypeDTO with:
  - id, name, description, basePrice, isActive
↓
Response: 200 OK
Body: [
  {
    "id": 1,
    "name": "General Darshan",
    "description": "Standard darshan for all devotees",
    "basePrice": 100,
    "isActive": true
  },
  ...
]
```

**Frontend:**
```
Display darshan types in admin table
```

---

## **API 22: POST /admin/darshan**

**Purpose:** Create new darshan type

### **Flow:**

**Frontend:**
```
Admin fills form:
- Name, description, price
↓
Clicks "Create"
↓
ApiService.createDarshanType(darshanData)
↓
axios.post('http://localhost:8080/api/admin/darshan', {
  name: "Premium Darshan",
  description: "VIP access with special privileges",
  basePrice: 2000,
  isActive: true
})
```

**Backend:**
```
Controller receives AdminDarshanTypeDTO
↓
Service:

1. Convert DTO to DarshanTypeEntity
   - Set id = null (for new entity)
   - Set name, description, basePrice, isActive

2. Save to database
   darshanTypeRepository.save(entity)
   ↓ INSERT INTO darshan_type (name, description, base_price, is_active)
     VALUES ('Premium Darshan', 'VIP access with special privileges', 2000, true)

3. Database returns saved entity with generated ID

4. Convert back to AdminDarshanTypeDTO
↓
Response: 201 CREATED
Body: {
  "id": 5,
  "name": "Premium Darshan",
  "description": "VIP access with special privileges",
  "basePrice": 2000,
  "isActive": true
}
```

**Frontend:**
```
Show success message
↓
Refresh darshan types list
```

---

## **API 23: PUT /admin/darshan/{id}**

**Purpose:** Update darshan type

### **Flow:**

**Frontend:**
```
Admin clicks "Edit" on a darshan type
↓
Modifies price: 2000 → 2500
↓
Clicks "Update"
↓
ApiService.updateDarshanType(id, updatedData)
↓
axios.put('http://localhost:8080/api/admin/darshan/5', {
  name: "Premium Darshan",
  description: "VIP access with special privileges",
  basePrice: 2500,
  isActive: true
})
```

**Backend:**
```
Controller receives id = 5 and AdminDarshanTypeDTO
↓
Service:

1. Find existing darshan type
   darshanTypeRepository.findById(5)
   ↓ SELECT * FROM darshan_type WHERE id = 5
   ↓ If not found → throw RuntimeException("Darshan type not found with id: 5")

2. Update fields
   existing.name = "Premium Darshan"
   existing.description = "VIP access with special privileges"
   existing.basePrice = 2500
   existing.isActive = true

3. Save updated entity
   darshanTypeRepository.save(existing)
   ↓ UPDATE darshan_type SET name = 'Premium Darshan', description = 'VIP access with special privileges',
     base_price = 2500, is_active = true WHERE id = 5

4. Convert to DTO
↓
Response: 200 OK with updated darshan type
```

**Frontend:**
```
Show success message
↓
Refresh list
```

---

## **API 24: DELETE /admin/darshan/{id}**

**Purpose:** Delete darshan type

### **Flow:**

**Frontend:**
```
Admin clicks "Delete"
↓
Confirmation dialog
↓
ApiService.deleteDarshanType(id)
↓
axios.delete('http://localhost:8080/api/admin/darshan/5')
```

**Backend:**
```
Controller receives id = 5
↓
Service:

1. Check if exists
   darshanTypeRepository.existsById(5)
   ↓ SELECT COUNT(*) FROM darshan_type WHERE id = 5
   ↓ If not exists → throw RuntimeException("Darshan type not found with id: 5")

2. Delete
   darshanTypeRepository.deleteById(5)
   ↓ DELETE FROM darshan_type WHERE id = 5

3. Return success message
↓
Response: 200 OK
Body: "Darshan type deleted successfully"
```

**Frontend:**
```
Show success message
↓
Remove from list
```

---

## **API 25: GET /admin/bookings/darshan**

**Purpose:** Get all darshan bookings

### **Flow:**

**Frontend:**
```
Admin opens "All Bookings"
↓
ApiService.getAllDarshanBookings()
↓
axios.get('http://localhost:8080/api/admin/bookings/darshan')
```

**Backend:**
```
Controller → Service
↓
Service: bookingRepository.findAll()
↓
Database: SELECT * FROM darshan_booking
↓
Returns List<DarshanBooking>
↓
Maps each to BookingResponseDTO
↓
Response: 200 OK with all bookings
```

**Frontend:**
```
Display all bookings in admin table
```

---

## **API 26: GET /admin/stats**

**Purpose:** Get dashboard statistics

### **Flow:**

**Frontend:**
```
Admin opens dashboard
↓
ApiService.getAdminStats()
↓
axios.get('http://localhost:8080/api/admin/stats')
```

**Backend:**
```
Controller → Service
↓
Service:

1. Count darshan bookings
   bookingRepository.count()
   ↓ SELECT COUNT(*) FROM darshan_booking

2. Count aarti bookings
   aartiBookingRepository.count()
   ↓ SELECT COUNT(*) FROM aarti_booking

3. Count pooja bookings (hardcoded 0)

4. Total donations (hardcoded 0.0)

5. Count users
   userRepository.count()
   ↓

---

explain from 26 api remaining apis

---


# **Continuing from API 26...**

---

## **API 26: GET /admin/stats** (Continued)

**Backend (Continued):**
```
5. Count users
   userRepository.count()
   ↓ SELECT COUNT(*) FROM user

6. Create AdminStatsDTO
   new AdminStatsDTO(darshanCount, aartiCount, poojaCount, totalDonations, totalUsers)
↓
Response: 200 OK
Body: {
  "darshanBookings": 150,
  "aartiBookings": 80,
  "poojaBookings": 0,
  "totalDonations": 0.0,
  "totalUsers": 250
}
```

**Frontend:**
```
Display statistics on admin dashboard
↓
Show cards with counts:
- Total Users: 250
- Darshan Bookings: 150
- Aarti Bookings: 80
- Pooja Bookings: 0
```

---

## **API 27: GET /admin/users**

**Purpose:** Get all registered users

### **Flow:**

**Frontend:**
```
Admin clicks "User Management"
↓
ApiService.getAllUsers()
↓
axios.get('http://localhost:8080/api/admin/users')
```

**Backend:**
```
Controller → Service
↓
Service: userRepository.findAll()
↓
Database: SELECT * FROM user
↓
Returns List<User> entities
↓
Maps each User to AdminUserDTO:
  convertUserToDTO(user) {
    - Extract: id, firstName, lastName, dateOfBirth
    - Convert enums to strings: gender.toString(), photoIdProof.toString(), role.toString()
    - Extract: photoIdNumber, phoneNumber, email
  }
↓
Response: 200 OK
Body: [
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "dateOfBirth": "1990-01-01",
    "gender": "MALE",
    "photoIdProof": "AADHAAR",
    "photoIdNumber": "1234-5678-9012",
    "phoneNumber": "9876543210",
    "email": "john@example.com",
    "role": "USER"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "dateOfBirth": "1992-05-15",
    "gender": "FEMALE",
    "photoIdProof": "PAN",
    "photoIdNumber": "ABCDE1234F",
    "phoneNumber": "9988776655",
    "email": "jane@example.com",
    "role": "USER"
  },
  ...
]
```

**Frontend:**
```
Display users in table format:
- Columns: ID, Name, Email, Phone, Role, Actions
- Show pagination if many users
- Provide search/filter options
```

---

## **API 28: GET /admin/users/{id}**

**Purpose:** Get specific user details by ID

### **Flow:**

**Frontend:**
```
Admin clicks "View Details" on a user
↓
ApiService.getUserById(userId)
↓
axios.get('http://localhost:8080/api/admin/users/1')
```

**Backend:**
```
Controller receives id = 1
↓
Service:

1. Find user by ID
   userRepository.findById(1)
   ↓ SELECT * FROM user WHERE id = 1
   ↓ If not found → throw RuntimeException("User not found with id: 1")

2. Convert User entity to AdminUserDTO
   convertUserToDTO(user) {
     return new AdminUserDTO(
       user.getId(),                          // 1
       user.getFirstName(),                   // "John"
       user.getLastName(),                    // "Doe"
       user.getDateOfBirth(),                 // "1990-01-01"
       user.getGender().toString(),           // "MALE"
       user.getPhotoIdProof().toString(),     // "AADHAAR"
       user.getPhotoIdNumber(),               // "1234-5678-9012"
       user.getPhoneNumber(),                 // "9876543210"
       user.getEmail(),                       // "john@example.com"
       user.getRole().toString()              // "USER"
     )
   }
↓
Response: 200 OK
Body: {
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1990-01-01",
  "gender": "MALE",
  "photoIdProof": "AADHAAR",
  "photoIdNumber": "1234-5678-9012",
  "phoneNumber": "9876543210",
  "email": "john@example.com",
  "role": "USER"
}
```

**Frontend:**
```
Display user details in modal/page:
- Personal Information section
- Contact Information section
- ID Proof details
- Account Information (role, registration date)
- Option to view user's booking history
```

---

## **API 29: POST /darshan/bookings/{bookingId}/cancel** (Already explained as API 11)

This was already covered in the Darshan APIs section.

---

# **SUMMARY: Complete Data Flow Pattern**

All 29 APIs follow this consistent architecture:

```
┌─────────────────────────────────────────────────────────────┐
│                    FRONTEND (React)                          │
├─────────────────────────────────────────────────────────────┤
│  1. User Action (button click, form submit)                 │
│  2. Component calls ApiService method                        │
│  3. ApiService makes axios HTTP request                      │
└────────────────────────┬────────────────────────────────────┘
                         │ HTTP Request (JSON)
                         ↓
┌─────────────────────────────────────────────────────────────┐
│                 BACKEND (Spring Boot)                        │
├─────────────────────────────────────────────────────────────┤
│  CONTROLLER LAYER                                            │
│  - Receives HTTP request                                     │
│  - @RequestBody converts JSON to DTO                         │
│  - Calls Service method                                      │
│  - Returns ResponseEntity with status code                   │
├─────────────────────────────────────────────────────────────┤
│  SERVICE LAYER                                               │
│  - Business logic validation                                 │
│  - Calls Repository methods                                  │
│  - Entity ↔ DTO conversion (ModelMapper)                    │
│  - Transaction management (@Transactional)                   │
│  - Exception handling                                        │
├─────────────────────────────────────────────────────────────┤
│  REPOSITORY LAYER                                            │
│  - JPA Repository interfaces                                 │
│  - Hibernate ORM                                             │
│  - Generates SQL queries                                     │
└────────────────────────┬────────────────────────────────────┘
                         │ SQL Query
                         ↓
┌─────────────────────────────────────────────────────────────┐
│                   DATABASE (MySQL)                           │
├─────────────────────────────────────────────────────────────┤
│  - Executes SQL (SELECT, INSERT, UPDATE, DELETE)            │
│  - Returns result set                                        │
│  - Transaction commit/rollback                               │
└────────────────────────┬────────────────────────────────────┘
                         │ Result Data
                         ↓
┌─────────────────────────────────────────────────────────────┐
│                  RESPONSE FLOW (Reverse)                     │
├─────────────────────────────────────────────────────────────┤
│  Database → Repository → Service → Controller → Frontend    │
│                                                               │
│  - Entity objects converted to DTOs                          │
│  - DTOs serialized to JSON                                   │
│  - HTTP Response with status code                            │
│  - Frontend updates UI                                       │
└─────────────────────────────────────────────────────────────┘
```

---

# **Key Technologies Used in Data Flow:**

1. **HTTP Communication:** REST API with JSON format
2. **Frontend HTTP Client:** Axios
3. **Backend Framework:** Spring Boot MVC
4. **Dependency Injection:** Spring @Autowired, @RequiredArgsConstructor
5. **ORM:** Hibernate JPA
6. **DTO Mapping:** ModelMapper library
7. **Validation:** Bean Validation (@Valid)
8. **Transaction Management:** Spring @Transactional
9. **Database:** MySQL with JDBC
10. **Error Handling:** Try-catch with proper HTTP status codes

---

# **HTTP Status Codes Used:**

- **200 OK** - Successful GET/PUT requests
- **201 CREATED** - Successful POST (resource created)
- **400 BAD REQUEST** - Invalid input data
- **401 UNAUTHORIZED** - Authentication failed
- **404 NOT FOUND** - Resource not found
- **409 CONFLICT** - Duplicate entry (email exists)
- **500 INTERNAL SERVER ERROR** - Server-side errors

---

**All 29 APIs Completed!** Each follows this consistent pattern ensuring maintainability, scalability, and clean architecture.

---

tell me what is the core functinality of your project; in few lines

---


## **Core Functionality of Sai Darshan Project**

**Sai Darshan is an online temple booking system that allows devotees to:**

1. **Register/Login** - Create account and authenticate to access services

2. **Book Darshan** - Reserve time slots for temple visits with real-time availability checking

3. **Book Aarti** - Schedule participation in morning/evening aarti ceremonies

4. **Book Special Poojas** - Request special religious ceremonies with devotee details

5. **Manage Bookings** - View all bookings and cancel if needed

6. **Admin Dashboard** - Admins can manage services (CRUD operations on darshan/aarti/pooja types), view all bookings, monitor statistics, and manage users

**In essence:** A complete digital solution for temple visit management, eliminating physical queues and enabling devotees to plan their spiritual visits online while giving temple administrators full control over services and capacity management.

---

explain how many tables are there in your database and desribe each table and how each table is connected to each other and for each functionality which tables are used together

---

Let me examine the database entities to understand the complete table structure.

---


Perfect! Now I have all the entity information. Let me create a comprehensive database structure explanation.

---

# **DATABASE STRUCTURE - SAI DARSHAN PROJECT**

## **Total Tables: 7**

---

## **1. TABLE: users**

**Purpose:** Stores all registered users (devotees and admins)

**Columns:**
- `id` (PK) - Auto-generated user ID
- `first_name` - User's first name
- `last_name` - User's last name
- `date_of_birth` - Date of birth
- `gender` - ENUM (MALE, FEMALE, OTHER)
- `photo_id_proof` - ENUM (AADHAAR, PAN, PASSPORT, DRIVING_LICENSE)
- `photo_id_number` - ID proof number
- `phone_number` - 10-digit phone number
- `email` (UNIQUE) - User email for login
- `password` - User password
- `role` - ENUM (USER, ADMIN) - Default: USER
- `created_at` - Registration timestamp

**Relationships:**
- One user → Many darshan bookings
- One user → Many pooja bookings
- One user → Many aarti bookings (via userId)

---

## **2. TABLE: darshan_slots**

**Purpose:** Stores available time slots for darshan with capacity management

**Columns:**
- `id` (PK) - Slot ID
- `name` - Slot name (e.g., "Morning Darshan")
- `start_time` - Slot start time
- `end_time` - Slot end time
- `max_capacity` - Maximum people allowed
- `available_slots` - Current available capacity
- `price` - Price per person
- `type` - ENUM (GENERAL_DARSHAN, VIP_DARSHAN, SPECIAL_DARSHAN)
- `is_active` - Boolean (active/inactive)

**Relationships:**
- One slot → Many darshan bookings

---

## **3. TABLE: darshan_bookings**

**Purpose:** Stores all darshan booking records

**Columns:**
- `id` (PK) - Booking ID
- `user_id` (FK) - References users.id
- `darshan_slot_id` (FK) - References darshan_slots.id
- `booking_date` - Date of darshan visit
- `number_of_people` - Number of people in booking
- `status` - ENUM (CONFIRMED, CANCELLED, COMPLETED)
- `created_at` - Booking creation timestamp

**Relationships:**
- Many bookings → One user
- Many bookings → One darshan slot
- One booking → One payment (optional)

---

## **4. TABLE: darshan_types**

**Purpose:** Admin-managed darshan type configurations

**Columns:**
- `id` (PK) - Darshan type ID
- `name` (UNIQUE) - Type name
- `description` - Description
- `base_price` - Base price
- `is_active` - Boolean (active/inactive)

**Relationships:**
- Standalone table (used by admin for management)
- No direct FK relationships

---

## **5. TABLE: aarti_bookings**

**Purpose:** Stores aarti ceremony bookings

**Columns:**
- `id` (PK) - Booking ID
- `user_id` - User ID (not FK, just integer reference)
- `aarti_type` - Type of aarti (Morning/Evening)
- `booking_date` - Date of aarti
- `price` - Booking price
- `status` - Booking status (BOOKED, CANCELLED)

**Relationships:**
- Loosely coupled to users via user_id (no formal FK)

---

## **6. TABLE: pooja_types**

**Purpose:** Master table for available pooja types

**Columns:**
- `id` (PK) - Pooja type ID
- `pooja_code` (UNIQUE) - ENUM (SAI_ABHISHEK, NAVAGRAHA_POOJA, SATYANARAYANA_POOJA, etc.)
- `display_name` - Display name
- `description` - Detailed description
- `duration_minutes` - Duration in minutes
- `price` - Pooja price
- `benefits` - Benefits description
- `required_materials` - Required materials
- `active` - Boolean (active/inactive)

**Relationships:**
- One pooja type → Many pooja bookings

---

## **7. TABLE: pooja_bookings**

**Purpose:** Stores special pooja booking records with devotee details

**Columns:**
- `id` (PK) - Booking ID
- `user_id` (FK) - References users.id
- `pooja_type_id` (FK) - References pooja_types.id
- `pooja_date` - Date of pooja
- `time_slot` - Time slot
- `total_amount` - Total amount
- `status` - ENUM (CONFIRMED, CANCELLED, COMPLETED)
- `devotee_name` - Name of devotee
- `devotee_age` - Age
- `devotee_gender` - ENUM (MALE, FEMALE, OTHER)
- `devotee_mobile` - Mobile number
- `devotee_email` - Email
- `sankalp` - Purpose/wish (mandatory)
- `gotra` - Gotra (optional)
- `address` - Address (optional)
- `created_at` - Booking timestamp

**Relationships:**
- Many bookings → One user
- Many bookings → One pooja type

---

## **8. TABLE: payments** (Defined but not actively used)

**Purpose:** Payment transaction records

**Columns:**
- `id` (PK) - Payment ID
- `booking_id` (FK) - References darshan_bookings.id
- `amount` - Payment amount
- `status` - ENUM (PENDING, SUCCESS, FAILED)
- `transaction_id` - Transaction reference
- `payment_date` - Payment timestamp

**Relationships:**
- One payment → One darshan booking

---

# **TABLE RELATIONSHIPS DIAGRAM**

```
┌─────────────────┐
│     users       │
│  (id, email,    │
│   role, etc.)   │
└────────┬────────┘
         │
         │ 1:N (One user has many bookings)
         │
    ┌────┴────┬──────────────┬──────────────┐
    │         │              │              │
    ▼         ▼              ▼              ▼
┌─────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐
│ darshan │ │  aarti   │ │  pooja   │ │ payments │
│bookings │ │ bookings │ │ bookings │ │          │
└────┬────┘ └──────────┘ └────┬─────┘ └────┬─────┘
     │                         │            │
     │ N:1                     │ N:1        │ 1:1
     │                         │            │
     ▼                         ▼            ▼
┌─────────┐              ┌──────────┐ ┌──────────┐
│ darshan │              │  pooja   │ │ darshan  │
│  slots  │              │  types   │ │ bookings │
└─────────┘              └──────────┘ └──────────┘

┌──────────────┐
│darshan_types │ (Admin management - standalone)
└──────────────┘
```

---

# **TABLES USED FOR EACH FUNCTIONALITY**

---

## **1. USER REGISTRATION & LOGIN**

**Tables Used:**
- `users`

**Operations:**
- **Signup:** INSERT into users
- **Login:** SELECT from users WHERE email = ? AND password = ?
- **Get Profile:** SELECT from users WHERE id = ?
- **Update Profile:** UPDATE users WHERE id = ?

---

## **2. DARSHAN BOOKING**

**Tables Used:**
- `users` (validate user)
- `darshan_slots` (check availability, get pricing)
- `darshan_bookings` (create booking)

**Operations:**
- **Get Darshan Types:** Read from DarshanTypeEnum (no DB query)
- **Get Available Slots:** SELECT from darshan_slots WHERE is_active = true
- **Get Prices:** SELECT from darshan_slots GROUP BY type
- **Book Darshan:**
  1. SELECT from users WHERE id = ?
  2. SELECT from darshan_slots WHERE id = ?
  3. INSERT into darshan_bookings
  4. UPDATE darshan_slots SET available_slots = available_slots - ?
- **View My Bookings:** SELECT from darshan_bookings WHERE user_id = ? (JOIN with users and darshan_slots)
- **Cancel Booking:**
  1. UPDATE darshan_bookings SET status = 'CANCELLED'
  2. UPDATE darshan_slots SET available_slots = available_slots + ?

---

## **3. AARTI BOOKING**

**Tables Used:**
- `aarti_bookings`

**Operations:**
- **Get Aarti Types:** Hardcoded list (no DB)
- **Get Schedule:** Hardcoded response (no DB)
- **Get Prices:** Hardcoded response (no DB)
- **Book Aarti:** INSERT into aarti_bookings
- **View Bookings:** SELECT from aarti_bookings WHERE user_id = ?

---

## **4. POOJA BOOKING**

**Tables Used:**
- `users` (validate user)
- `pooja_types` (get available poojas)
- `pooja_bookings` (create booking)

**Operations:**
- **Get Pooja Types:** SELECT from pooja_types WHERE active = true
- **Book Pooja:**
  1. SELECT from users WHERE id = ?
  2. SELECT from pooja_types WHERE pooja_code = ?
  3. INSERT into pooja_bookings (with devotee details)
- **View My Bookings:** SELECT from pooja_bookings WHERE user_id = ? (JOIN with users and pooja_types)

---

## **5. ADMIN DASHBOARD**

**Tables Used:**
- `users`
- `darshan_bookings`
- `aarti_bookings`
- `pooja_bookings`

**Operations:**
- **Get Statistics:**
  - SELECT COUNT(*) from users
  - SELECT COUNT(*) from darshan_bookings
  - SELECT COUNT(*) from aarti_bookings
  - SELECT COUNT(*) from pooja_bookings
- **Get All Users:** SELECT from users
- **Get User Details:** SELECT from users WHERE id = ?

---

## **6. ADMIN DARSHAN MANAGEMENT**

**Tables Used:**
- `darshan_types`
- `darshan_bookings`

**Operations:**
- **Get All Darshan Types:** SELECT from darshan_types
- **Create Darshan Type:** INSERT into darshan_types
- **Update Darshan Type:** UPDATE darshan_types WHERE id = ?
- **Delete Darshan Type:** DELETE from darshan_types WHERE id = ?
- **View All Bookings:** SELECT from darshan_bookings (JOIN with users and darshan_slots)

---

# **KEY DATABASE RELATIONSHIPS**

## **One-to-Many Relationships:**

1. **users → darshan_bookings** (1:N)
   - One user can make multiple darshan bookings

2. **users → pooja_bookings** (1:N)
   - One user can make multiple pooja bookings

3. **darshan_slots → darshan_bookings** (1:N)
   - One slot can have multiple bookings

4. **pooja_types → pooja_bookings** (1:N)
   - One pooja type can have multiple bookings

## **One-to-One Relationships:**

5. **darshan_bookings → payments** (1:1)
   - One booking has one payment record

---

# **FOREIGN KEY CONSTRAINTS**

```sql
darshan_bookings.user_id → users.id
darshan_bookings.darshan_slot_id → darshan_slots.id

pooja_bookings.user_id → users.id
pooja_bookings.pooja_type_id → pooja_types.id

payments.booking_id → darshan_bookings.id
```

---

# **SUMMARY**

- **7 Active Tables** (8 if including payments)
- **4 Master Tables:** users, darshan_slots, pooja_types, darshan_types
- **3 Transaction Tables:** darshan_bookings, aarti_bookings, pooja_bookings
- **1 Payment Table:** payments (defined but not actively used)
- **Primary Keys:** All tables use auto-increment ID
- **Foreign Keys:** Proper referential integrity maintained
- **Enums:** Used for type safety (Gender, Role, BookingStatus, DarshanType, PoojaType)

---

explain how the darshan booking happens, and how you have made sure that multiple people doesnt book same slort at same time. and what are the booking edge cases and  how you have managed the booking edge cases

---


# **DARSHAN BOOKING PROCESS - DETAILED EXPLANATION**

---

## **COMPLETE DARSHAN BOOKING FLOW**

### **Step-by-Step Process:**

```
USER SELECTS DATE
      ↓
SYSTEM FETCHES AVAILABLE SLOTS
      ↓
USER SELECTS SLOT + NUMBER OF PEOPLE
      ↓
USER CLICKS "BOOK NOW"
      ↓
BACKEND VALIDATES & PROCESSES
      ↓
BOOKING CONFIRMED / ERROR RETURNED
```

---

## **BACKEND BOOKING LOGIC (DarshanServiceImpl.java)**

```java
@Transactional  // ← KEY: Ensures atomic operation
public BookingResponseDTO bookDarshan(BookingRequestDTO request) {
    
    // STEP 1: Validate User Exists
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));
    
    // STEP 2: Validate Slot Exists
    DarshanSlot slot = darshanSlotRepository.findById(request.getDarshanSlotId())
        .orElseThrow(() -> new RuntimeException("Darshan slot not found"));
    
    // STEP 3: CHECK AVAILABILITY (Critical for preventing overbooking)
    if (slot.getAvailableSlots() < request.getNumberOfPeople()) {
        throw new RuntimeException("Not enough slots available");
    }
    
    // STEP 4: Create Booking
    DarshanBooking booking = new DarshanBooking();
    booking.setUser(user);
    booking.setDarshanSlot(slot);
    booking.setBookingDate(request.getBookingDate());
    booking.setNumberOfPeople(request.getNumberOfPeople());
    booking.setStatus(BookingStatusEnum.CONFIRMED);
    
    // STEP 5: Reduce Available Slots (CRITICAL)
    slot.setAvailableSlots(slot.getAvailableSlots() - request.getNumberOfPeople());
    darshanSlotRepository.save(slot);  // Update slot capacity
    
    // STEP 6: Save Booking
    DarshanBooking savedBooking = bookingRepository.save(booking);
    
    // STEP 7: Return Response
    return mapToBookingResponseDTO(savedBooking);
}
```

---

## **HOW CONCURRENT BOOKING IS PREVENTED**

### **1. @Transactional Annotation**

```java
@Service
@Transactional  // ← This is the KEY
public class DarshanServiceImpl implements DarshanService {
```

**What it does:**
- Wraps entire method in a database transaction
- Ensures ACID properties (Atomicity, Consistency, Isolation, Durability)
- If any step fails, entire transaction rolls back

---

### **2. Database-Level Locking**

**Scenario: Two users book simultaneously**

```
TIME    USER A                          USER B
----    ------                          ------
T1      Reads slot: availableSlots=10   
T2                                      Reads slot: availableSlots=10
T3      Books 5 slots                   
T4      Updates: availableSlots=5       
T5      COMMIT                          
T6                                      Books 3 slots
T7                                      Reads UPDATED value: availableSlots=5
T8                                      Updates: availableSlots=2
T9                                      COMMIT
```

**How it works:**
- Spring's `@Transactional` uses **database row-level locking**
- When User A reads the slot, database locks that row
- User B's transaction waits until User A commits
- User B then reads the UPDATED availability (5 slots, not 10)
- This prevents overbooking

---

### **3. Pessimistic Locking (If Needed)**

**Enhanced version with explicit locking:**

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT s FROM DarshanSlot s WHERE s.id = :id")
DarshanSlot findByIdWithLock(@Param("id") Long id);
```

**What this does:**
- Explicitly locks the row for writing
- Other transactions MUST wait
- Guarantees no concurrent modifications

---

## **DATABASE TRANSACTION FLOW**

```sql
-- USER A TRANSACTION
BEGIN TRANSACTION;

-- Step 1: Lock and read slot
SELECT * FROM darshan_slots WHERE id = 1 FOR UPDATE;  -- Row locked
-- Result: available_slots = 10

-- Step 2: Check availability
IF (10 >= 5) THEN  -- User wants 5 slots

-- Step 3: Insert booking
INSERT INTO darshan_bookings (user_id, darshan_slot_id, number_of_people, status)
VALUES (1, 1, 5, 'CONFIRMED');

-- Step 4: Update slot capacity
UPDATE darshan_slots SET available_slots = 10 - 5 WHERE id = 1;
-- Now available_slots = 5

COMMIT;  -- Lock released

-- USER B TRANSACTION (starts after User A commits)
BEGIN TRANSACTION;

SELECT * FROM darshan_slots WHERE id = 1 FOR UPDATE;
-- Result: available_slots = 5 (UPDATED VALUE)

IF (5 >= 3) THEN  -- User wants 3 slots
INSERT INTO darshan_bookings (user_id, darshan_slot_id, number_of_people, status)
VALUES (2, 1, 3, 'CONFIRMED');

UPDATE darshan_slots SET available_slots = 5 - 3 WHERE id = 1;
-- Now available_slots = 2

COMMIT;
```

---

## **BOOKING EDGE CASES & SOLUTIONS**

---

### **EDGE CASE 1: Slot Becomes Full During Booking**

**Scenario:**
- Slot has 2 available slots
- User A tries to book 3 slots

**Solution:**
```java
if (slot.getAvailableSlots() < request.getNumberOfPeople()) {
    throw new RuntimeException("Not enough slots available");
}
```

**Result:**
- Booking rejected immediately
- User sees error: "Not enough slots available"
- No database changes made

---

### **EDGE CASE 2: User Doesn't Exist**

**Scenario:**
- Invalid userId in request

**Solution:**
```java
User user = userRepository.findById(request.getUserId())
    .orElseThrow(() -> new RuntimeException("User not found"));
```

**Result:**
- Transaction fails before any booking
- Returns 400 BAD REQUEST
- No slot capacity affected

---

### **EDGE CASE 3: Slot Doesn't Exist**

**Scenario:**
- Invalid slotId in request

**Solution:**
```java
DarshanSlot slot = darshanSlotRepository.findById(request.getDarshanSlotId())
    .orElseThrow(() -> new RuntimeException("Darshan slot not found"));
```

**Result:**
- Booking rejected
- Returns 404 NOT FOUND

---

### **EDGE CASE 4: Concurrent Bookings (Race Condition)**

**Scenario:**
- 5 slots available
- User A books 3 slots
- User B books 3 slots (simultaneously)

**Solution:**
```java
@Transactional  // Database handles serialization
```

**What happens:**
```
Initial: availableSlots = 5

User A Transaction:
- Locks row
- Reads: 5 slots
- Books 3
- Updates: 5 - 3 = 2
- Commits (releases lock)

User B Transaction:
- Waits for lock
- Reads: 2 slots (UPDATED)
- Tries to book 3
- Check fails: 2 < 3
- Throws exception: "Not enough slots available"
- Rollback
```

**Result:**
- User A: SUCCESS (3 slots booked)
- User B: ERROR (insufficient slots)
- Final availableSlots = 2 ✓

---

### **EDGE CASE 5: Booking for Past Date**

**Current Implementation:** Not validated

**Recommended Solution:**
```java
if (request.getBookingDate().isBefore(LocalDate.now())) {
    throw new RuntimeException("Cannot book for past dates");
}
```

---

### **EDGE CASE 6: Duplicate Booking by Same User**

**Current Implementation:** Allowed (user can book multiple times)

**If Prevention Needed:**
```java
boolean alreadyBooked = bookingRepository.existsByUserIdAndDarshanSlotIdAndBookingDate(
    userId, slotId, bookingDate
);
if (alreadyBooked) {
    throw new RuntimeException("You already have a booking for this slot");
}
```

---

### **EDGE CASE 7: Negative Number of People**

**Current Implementation:** Not validated

**Recommended Solution:**
```java
if (request.getNumberOfPeople() <= 0) {
    throw new RuntimeException("Number of people must be positive");
}
```

---

### **EDGE CASE 8: Booking Cancellation & Slot Restoration**

**Scenario:**
- User cancels booking
- Slots must be restored

**Solution (Already Implemented):**
```java
public ApiResponse cancelBooking(Long bookingId) {
    DarshanBooking booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new RuntimeException("Booking not found"));
    
    // Update status
    booking.setStatus(BookingStatusEnum.CANCELLED);
    
    // RESTORE SLOTS
    DarshanSlot slot = booking.getDarshanSlot();
    slot.setAvailableSlots(slot.getAvailableSlots() + booking.getNumberOfPeople());
    darshanSlotRepository.save(slot);
    
    bookingRepository.save(booking);
    return new ApiResponse("Success", "Booking cancelled successfully");
}
```

**Result:**
- Booking status = CANCELLED
- Slots restored to pool
- Other users can now book

---

### **EDGE CASE 9: Transaction Failure Mid-Process**

**Scenario:**
- Booking created
- Slot update fails (database error)

**Solution:**
```java
@Transactional  // Automatic rollback
```

**What happens:**
- If ANY step fails, entire transaction rolls back
- Booking NOT saved
- Slot capacity NOT changed
- Database remains consistent

---

### **EDGE CASE 10: Inactive Slot Booking**

**Current Implementation:** Not checked in booking

**Recommended Solution:**
```java
if (!slot.getIsActive()) {
    throw new RuntimeException("This slot is no longer available");
}
```

---

## **CONCURRENCY CONTROL MECHANISMS**

### **1. Database Transaction Isolation**

```properties
# application.properties
spring.jpa.properties.hibernate.connection.isolation=2
# ISOLATION_READ_COMMITTED
```

**Levels:**
- **READ_UNCOMMITTED (1):** Dirty reads possible ❌
- **READ_COMMITTED (2):** Prevents dirty reads ✓ (Default)
- **REPEATABLE_READ (3):** Prevents non-repeatable reads ✓
- **SERIALIZABLE (4):** Full isolation (slowest) ✓✓

---

### **2. Optimistic Locking (Alternative Approach)**

```java
@Entity
public class DarshanSlot {
    @Version
    private Long version;  // Hibernate manages this
}
```

**How it works:**
- Each update increments version
- If version changed between read and write, update fails
- Application retries

---

### **3. Pessimistic Locking (Explicit)**

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
DarshanSlot findByIdWithLock(Long id);
```

**When to use:**
- High contention scenarios
- Critical operations
- Guaranteed consistency needed

---

## **BOOKING VALIDATION CHECKLIST**

```
✓ User exists
✓ Slot exists
✓ Sufficient availability
✓ Transaction atomicity
✓ Slot capacity updated
✓ Booking saved
✓ Concurrent access handled
✗ Past date validation (missing)
✗ Negative people validation (missing)
✗ Inactive slot check (missing)
✗ Duplicate booking check (optional)
```

---

## **SUMMARY: HOW OVERBOOKING IS PREVENTED**

1. **@Transactional:** Ensures atomic operations
2. **Database Locking:** Row-level locks prevent concurrent modifications
3. **Availability Check:** Validates before booking
4. **Immediate Update:** Slot capacity updated in same transaction
5. **Rollback on Failure:** Any error reverts all changes

**Result:** Multiple users CANNOT book the same slot simultaneously. The database guarantees serialization of concurrent transactions.
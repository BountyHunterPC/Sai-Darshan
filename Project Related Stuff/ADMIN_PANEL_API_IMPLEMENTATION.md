# Admin Panel API Implementation

## Overview
Implemented complete CRUD APIs for Aarti and Pooja type management in the admin panel, matching the existing Darshan type management pattern.

## New Files Created

### 1. Entities
- **AartiType.java** - Entity for managing aarti types with fields: id, name, description, timings, price, isActive

### 2. Repositories
- **AartiTypeRepository.java** - JPA repository for AartiType with method to find active aarti types

### 3. DTOs
- **AdminAartiTypeDTO.java** - DTO for aarti type CRUD operations
- **AdminPoojaTypeDTO.java** - DTO for pooja type CRUD operations

## Updated Files

### 1. AdminService.java
Added method signatures for:
- `getAllAartiTypes()` - Get all aarti types
- `createAartiType(AdminAartiTypeDTO)` - Create new aarti type
- `updateAartiType(Long id, AdminAartiTypeDTO)` - Update existing aarti type
- `deleteAartiType(Long id)` - Delete aarti type
- `getAllPoojaTypes()` - Get all pooja types
- `createPoojaType(AdminPoojaTypeDTO)` - Create new pooja type
- `updatePoojaType(Long id, AdminPoojaTypeDTO)` - Update existing pooja type
- `deletePoojaType(Long id)` - Delete pooja type

### 2. AdminServiceImpl.java
Implemented all aarti and pooja type management methods with:
- Entity to DTO conversion methods
- DTO to Entity conversion methods
- Full CRUD logic with error handling

### 3. AdminController.java
Added REST endpoints:

#### Aarti Management
- `GET /admin/aarti` - Get all aarti types
- `POST /admin/aarti` - Create new aarti type
- `PUT /admin/aarti/{id}` - Update aarti type
- `DELETE /admin/aarti/{id}` - Delete aarti type

#### Pooja Management
- `GET /admin/pooja` - Get all pooja types
- `POST /admin/pooja` - Create new pooja type
- `PUT /admin/pooja/{id}` - Update pooja type
- `DELETE /admin/pooja/{id}` - Delete pooja type

## API Details

### Aarti Type Structure
```json
{
  "id": 1,
  "name": "Kakad Aarti",
  "description": "Morning aarti performed at 4:30 AM",
  "timings": "4:30 AM",
  "price": 200.0,
  "isActive": true
}
```

### Pooja Type Structure
```json
{
  "id": 1,
  "poojaCode": "ABHISHEK",
  "displayName": "Sai Abhishek Pooja",
  "description": "Sacred abhishek ceremony",
  "durationMinutes": 60,
  "price": 500.0,
  "benefits": "Spiritual blessings and peace",
  "requiredMaterials": "Flowers, fruits, incense",
  "active": true
}
```

## Security
All admin endpoints are protected by Spring Security:
- Requires ADMIN role
- JWT token authentication
- Endpoints: `/admin/aarti/**` and `/admin/pooja/**`

## Database Tables
- **aarti_types** - Auto-created by Hibernate for AartiType entity
- **pooja_types** - Already exists, used by PoojaType entity

## Testing
After backend restart, you can:
1. Login as admin (admin@shirdi.com / password123)
2. Access admin panel
3. Use "Add Aarti" button to create aarti types
4. Use "Add Pooja" button to create pooja types
5. Edit/Delete existing aarti and pooja types

## Notes
- All endpoints follow the same pattern as darshan type management
- Error handling returns appropriate HTTP status codes
- Validation is handled at service layer
- Frontend should already have the UI components ready

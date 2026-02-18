package com.shirdi.darshan.service;

import com.shirdi.darshan.dtos.AdminAartiTypeDTO;
import com.shirdi.darshan.dtos.AdminDarshanSlotDTO;
import com.shirdi.darshan.dtos.AdminDarshanTypeDTO;
import com.shirdi.darshan.dtos.AdminPoojaTypeDTO;
import com.shirdi.darshan.dtos.AdminPoojaTimeSlotDTO;
import com.shirdi.darshan.dtos.AdminStatsDTO;
import com.shirdi.darshan.dtos.AdminUserDTO;
import com.shirdi.darshan.dtos.BookingResponseDTO;
import java.util.List;

public interface AdminService {
    List<AdminDarshanTypeDTO> getAllDarshanTypes();
    AdminDarshanTypeDTO createDarshanType(AdminDarshanTypeDTO darshanTypeDTO);
    AdminDarshanTypeDTO updateDarshanType(Long id, AdminDarshanTypeDTO darshanTypeDTO);
    void deleteDarshanType(Long id);
    List<BookingResponseDTO> getAllDarshanBookings();
    
    List<AdminDarshanSlotDTO> getAllDarshanSlots();
    AdminDarshanSlotDTO createDarshanSlot(AdminDarshanSlotDTO slotDTO);
    AdminDarshanSlotDTO updateDarshanSlot(Long id, AdminDarshanSlotDTO slotDTO);
    void deleteDarshanSlot(Long id);
    
    List<AdminAartiTypeDTO> getAllAartiTypes();
    AdminAartiTypeDTO createAartiType(AdminAartiTypeDTO aartiTypeDTO);
    AdminAartiTypeDTO updateAartiType(Long id, AdminAartiTypeDTO aartiTypeDTO);
    void deleteAartiType(Long id);
    List<BookingResponseDTO> getAllAartiBookings();
    
    List<AdminPoojaTypeDTO> getAllPoojaTypes();
    AdminPoojaTypeDTO createPoojaType(AdminPoojaTypeDTO poojaTypeDTO);
    AdminPoojaTypeDTO updatePoojaType(Long id, AdminPoojaTypeDTO poojaTypeDTO);
    void deletePoojaType(Long id);
    
    List<AdminPoojaTimeSlotDTO> getAllPoojaTimeSlots();
    AdminPoojaTimeSlotDTO createPoojaTimeSlot(AdminPoojaTimeSlotDTO slotDTO);
    AdminPoojaTimeSlotDTO updatePoojaTimeSlot(Long id, AdminPoojaTimeSlotDTO slotDTO);
    void deletePoojaTimeSlot(Long id);
    List<BookingResponseDTO> getAllPoojaBookings();
    
    AdminStatsDTO getStatistics();
    List<AdminUserDTO> getAllUsers();
    AdminUserDTO getUserById(Long id);
}
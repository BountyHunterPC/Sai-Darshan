package com.shirdi.darshan.service;

import com.shirdi.darshan.dtos.AdminAartiTypeDTO;
import com.shirdi.darshan.dtos.AdminDarshanSlotDTO;
import com.shirdi.darshan.dtos.AdminDarshanTypeDTO;
import com.shirdi.darshan.dtos.AdminPoojaTypeDTO;
import com.shirdi.darshan.dtos.AdminPoojaTimeSlotDTO;
import com.shirdi.darshan.dtos.AdminStatsDTO;
import com.shirdi.darshan.dtos.AdminUserDTO;
import com.shirdi.darshan.dtos.BookingResponseDTO;
import com.shirdi.darshan.entities.AartiType;
import com.shirdi.darshan.entities.DarshanSlot;
import com.shirdi.darshan.entities.DarshanTypeEntity;
import com.shirdi.darshan.entities.PoojaType;
import com.shirdi.darshan.entities.PoojaTimeSlot;
import com.shirdi.darshan.entities.User;
import com.shirdi.darshan.enums.DarshanTypeEnum;
import com.shirdi.darshan.enums.PoojaTypeEnum;
import com.shirdi.darshan.repository.AartiBookingRepository;
import com.shirdi.darshan.repository.AartiTypeRepository;
import com.shirdi.darshan.repository.BookingRepository;
import com.shirdi.darshan.repository.DarshanSlotRepository;
import com.shirdi.darshan.repository.DarshanTypeRepository;
import com.shirdi.darshan.repository.PoojaBookingRepository;
import com.shirdi.darshan.repository.PoojaTypeRepository;
import com.shirdi.darshan.repository.PoojaTimeSlotRepository;
import com.shirdi.darshan.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    
    private final DarshanTypeRepository darshanTypeRepository;
    private final DarshanSlotRepository darshanSlotRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final AartiBookingRepository aartiBookingRepository;
    private final AartiTypeRepository aartiTypeRepository;
    private final PoojaTypeRepository poojaTypeRepository;
    private final PoojaBookingRepository poojaBookingRepository;
    private final PoojaTimeSlotRepository poojaTimeSlotRepository;
    private final DarshanService darshanService;

//    AdminServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public List<AdminDarshanTypeDTO> getAllDarshanTypes() {
        return darshanTypeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDarshanTypeDTO createDarshanType(AdminDarshanTypeDTO darshanTypeDTO) {
        DarshanTypeEntity entity = convertToEntity(darshanTypeDTO);
        entity.setId(null); // Ensure new entity
        DarshanTypeEntity saved = darshanTypeRepository.save(entity);
        return convertToDTO(saved);
    }

    @Override
    public AdminDarshanTypeDTO updateDarshanType(Long id, AdminDarshanTypeDTO darshanTypeDTO) {
        DarshanTypeEntity existing = darshanTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Darshan type not found with id: " + id));
        
        existing.setName(darshanTypeDTO.getName());
        existing.setDescription(darshanTypeDTO.getDescription());
        existing.setBasePrice(darshanTypeDTO.getBasePrice());
        existing.setIsActive(darshanTypeDTO.getIsActive());
        
        DarshanTypeEntity updated = darshanTypeRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void deleteDarshanType(Long id) {
        if (!darshanTypeRepository.existsById(id)) {
            throw new RuntimeException("Darshan type not found with id: " + id);
        }
        darshanTypeRepository.deleteById(id);
    }

    @Override
    public List<BookingResponseDTO> getAllDarshanBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> {
                    BookingResponseDTO dto = new BookingResponseDTO();
                    dto.setId(booking.getId());
                    dto.setUserId(booking.getUser().getId());
                    dto.setDarshanSlotId(booking.getDarshanSlot().getId());
                    dto.setDarshanSlotName(booking.getDarshanSlot().getName());
                    dto.setSlotTime(booking.getDarshanSlot().getStartTime() + " - " + booking.getDarshanSlot().getEndTime());
                    dto.setBookingDate(booking.getBookingDate());
                    dto.setNumberOfPeople(booking.getNumberOfPeople());
                    dto.setTotalAmount(booking.getDarshanSlot().getPrice() * booking.getNumberOfPeople());
                    dto.setCreatedAt(booking.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private AdminDarshanTypeDTO convertToDTO(DarshanTypeEntity entity) {
        return new AdminDarshanTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getBasePrice(),
                entity.getIsActive()
        );
    }

    private DarshanTypeEntity convertToEntity(AdminDarshanTypeDTO dto) {
        return new DarshanTypeEntity(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getBasePrice(),
                dto.getIsActive()
        );
    }

    @Override
    public List<AdminDarshanSlotDTO> getAllDarshanSlots() {
        return darshanSlotRepository.findAll().stream()
                .map(this::convertSlotToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDarshanSlotDTO createDarshanSlot(AdminDarshanSlotDTO slotDTO) {
        DarshanSlot slot = convertSlotToEntity(slotDTO);
        slot.setId(null);
        slot.setAvailableSlots(slotDTO.getMaxCapacity());
        DarshanSlot saved = darshanSlotRepository.save(slot);
        return convertSlotToDTO(saved);
    }

    @Override
    public AdminDarshanSlotDTO updateDarshanSlot(Long id, AdminDarshanSlotDTO slotDTO) {
        DarshanSlot existing = darshanSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Darshan slot not found with id: " + id));
        
        existing.setName(slotDTO.getName());
        existing.setStartTime(LocalTime.parse(slotDTO.getStartTime()));
        existing.setEndTime(LocalTime.parse(slotDTO.getEndTime()));
        existing.setType(DarshanTypeEnum.valueOf(slotDTO.getType()));
        existing.setMaxCapacity(slotDTO.getMaxCapacity());
        existing.setPrice(slotDTO.getPrice());
        existing.setIsActive(slotDTO.getIsActive());
        
        DarshanSlot updated = darshanSlotRepository.save(existing);
        return convertSlotToDTO(updated);
    }

    @Override
    public void deleteDarshanSlot(Long id) {
        if (!darshanSlotRepository.existsById(id)) {
            throw new RuntimeException("Darshan slot not found with id: " + id);
        }
        darshanSlotRepository.deleteById(id);
    }

    private AdminDarshanSlotDTO convertSlotToDTO(DarshanSlot slot) {
        return new AdminDarshanSlotDTO(
                slot.getId(),
                slot.getName(),
                slot.getStartTime().toString(),
                slot.getEndTime().toString(),
                slot.getType().name(),
                slot.getMaxCapacity(),
                slot.getAvailableSlots(),
                slot.getPrice(),
                slot.getIsActive()
        );
    }

    private DarshanSlot convertSlotToEntity(AdminDarshanSlotDTO dto) {
        DarshanSlot slot = new DarshanSlot();
        slot.setId(dto.getId());
        slot.setName(dto.getName());
        slot.setStartTime(LocalTime.parse(dto.getStartTime()));
        slot.setEndTime(LocalTime.parse(dto.getEndTime()));
        slot.setType(DarshanTypeEnum.valueOf(dto.getType()));
        slot.setMaxCapacity(dto.getMaxCapacity());
        slot.setAvailableSlots(dto.getAvailableSlots() != null ? dto.getAvailableSlots() : dto.getMaxCapacity());
        slot.setPrice(dto.getPrice());
        slot.setIsActive(dto.getIsActive());
        return slot;
    }

	@Override
	public AdminStatsDTO getStatistics() {
		Long darshanCount = bookingRepository.count();
        Long aartiCount = aartiBookingRepository.count();
        Long poojaCount = 0L;
        Double totalDonations = 0.0;
        Long totalUsers = userRepository.count();
        return new AdminStatsDTO(darshanCount, aartiCount, poojaCount, totalDonations, totalUsers);
    }
	

	@Override
	public List<AdminUserDTO> getAllUsers() {
		return userRepository.findAll().stream()
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
	}

	@Override
	public AdminUserDTO getUserById(Long id) {
		User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertUserToDTO(user);
	}

	private AdminUserDTO convertUserToDTO(User user) {
    return new AdminUserDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getDateOfBirth(),
            user.getGender().toString(),
            user.getPhotoIdProof().toString(),
            user.getPhotoIdNumber(),
            user.getPhoneNumber(),
            user.getEmail(),
            user.getRole().toString(),
            user.getCreatedAt()
    );
}

    @Override
    public List<AdminAartiTypeDTO> getAllAartiTypes() {
        return aartiTypeRepository.findAll().stream()
                .map(this::convertAartiToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminAartiTypeDTO createAartiType(AdminAartiTypeDTO aartiTypeDTO) {
        AartiType entity = convertAartiToEntity(aartiTypeDTO);
        entity.setId(null);
        AartiType saved = aartiTypeRepository.save(entity);
        return convertAartiToDTO(saved);
    }

    @Override
    public AdminAartiTypeDTO updateAartiType(Long id, AdminAartiTypeDTO aartiTypeDTO) {
        AartiType existing = aartiTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aarti type not found with id: " + id));
        
        existing.setName(aartiTypeDTO.getName());
        existing.setDescription(aartiTypeDTO.getDescription());
        existing.setTimings(aartiTypeDTO.getTimings());
        existing.setPrice(aartiTypeDTO.getPrice());
        existing.setDurationMinutes(aartiTypeDTO.getDurationMinutes());
        existing.setGuidelines(aartiTypeDTO.getGuidelines());
        existing.setMaxCapacity(aartiTypeDTO.getMaxCapacity());
        existing.setIsActive(aartiTypeDTO.getIsActive());
        
        AartiType updated = aartiTypeRepository.save(existing);
        return convertAartiToDTO(updated);
    }

    @Override
    public void deleteAartiType(Long id) {
        if (!aartiTypeRepository.existsById(id)) {
            throw new RuntimeException("Aarti type not found with id: " + id);
        }
        aartiTypeRepository.deleteById(id);
    }

    private AdminAartiTypeDTO convertAartiToDTO(AartiType entity) {
        return new AdminAartiTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getTimings(),
                entity.getPrice(),
                entity.getDurationMinutes(),
                entity.getGuidelines(),
                entity.getMaxCapacity(),
                entity.getIsActive()
        );
    }

    private AartiType convertAartiToEntity(AdminAartiTypeDTO dto) {
        return new AartiType(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getTimings(),
                dto.getPrice(),
                dto.getDurationMinutes(),
                dto.getGuidelines(),
                dto.getMaxCapacity(),
                dto.getIsActive()
        );
    }

    @Override
    public List<BookingResponseDTO> getAllAartiBookings() {
        return aartiBookingRepository.findAll().stream()
                .map(booking -> {
                    BookingResponseDTO dto = new BookingResponseDTO();
                    dto.setId(Long.valueOf(booking.getId()));
                    dto.setUserId(Long.valueOf(booking.getUserId()));
                    dto.setDarshanSlotName(booking.getAartiType());
                    dto.setSlotTime(booking.getAartiTime() != null ? booking.getAartiTime() : "TBD");
                    dto.setBookingDate(booking.getBookingDate());
                    dto.setNumberOfPeople(booking.getNumberOfPeople());
                    dto.setTotalAmount(booking.getPrice());
                    // Set aartiType for frontend compatibility
                    dto.setAartiType(booking.getAartiType());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminPoojaTypeDTO> getAllPoojaTypes() {
        return poojaTypeRepository.findAll().stream()
                .map(this::convertPoojaToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminPoojaTypeDTO createPoojaType(AdminPoojaTypeDTO poojaTypeDTO) {
        PoojaType entity = convertPoojaToEntity(poojaTypeDTO);
        entity.setId(null);
        PoojaType saved = poojaTypeRepository.save(entity);
        return convertPoojaToDTO(saved);
    }

    @Override
    public AdminPoojaTypeDTO updatePoojaType(Long id, AdminPoojaTypeDTO poojaTypeDTO) {
        PoojaType existing = poojaTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pooja type not found with id: " + id));
        
        existing.setPoojaType(PoojaTypeEnum.valueOf(poojaTypeDTO.getPoojaCode()));
        existing.setDisplayName(poojaTypeDTO.getDisplayName());
        existing.setDescription(poojaTypeDTO.getDescription());
        existing.setDurationMinutes(poojaTypeDTO.getDurationMinutes());
        existing.setPrice(poojaTypeDTO.getPrice());
        existing.setBenefits(poojaTypeDTO.getBenefits());
        existing.setRequiredMaterials(poojaTypeDTO.getRequiredMaterials());
        existing.setActive(poojaTypeDTO.getActive());
        
        PoojaType updated = poojaTypeRepository.save(existing);
        return convertPoojaToDTO(updated);
    }

    @Override
    public void deletePoojaType(Long id) {
        if (!poojaTypeRepository.existsById(id)) {
            throw new RuntimeException("Pooja type not found with id: " + id);
        }
        poojaTypeRepository.deleteById(id);
    }

    private AdminPoojaTypeDTO convertPoojaToDTO(PoojaType entity) {
        return new AdminPoojaTypeDTO(
                entity.getId(),
                entity.getPoojaType().name(),
                entity.getDisplayName(),
                entity.getDescription(),
                entity.getDurationMinutes(),
                entity.getPrice(),
                entity.getBenefits(),
                entity.getRequiredMaterials(),
                entity.getActive()
        );
    }

    private PoojaType convertPoojaToEntity(AdminPoojaTypeDTO dto) {
        return new PoojaType(
                dto.getId(),
                PoojaTypeEnum.valueOf(dto.getPoojaCode()),
                dto.getDisplayName(),
                dto.getDescription(),
                dto.getDurationMinutes(),
                dto.getPrice(),
                dto.getBenefits(),
                dto.getRequiredMaterials(),
                dto.getActive()
        );
    }

    @Override
    public List<AdminPoojaTimeSlotDTO> getAllPoojaTimeSlots() {
        return poojaTimeSlotRepository.findAll().stream()
                .map(this::convertPoojaTimeSlotToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminPoojaTimeSlotDTO createPoojaTimeSlot(AdminPoojaTimeSlotDTO slotDTO) {
        PoojaTimeSlot entity = convertPoojaTimeSlotToEntity(slotDTO);
        entity.setId(null);
        PoojaTimeSlot saved = poojaTimeSlotRepository.save(entity);
        return convertPoojaTimeSlotToDTO(saved);
    }

    @Override
    public AdminPoojaTimeSlotDTO updatePoojaTimeSlot(Long id, AdminPoojaTimeSlotDTO slotDTO) {
        PoojaTimeSlot existing = poojaTimeSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pooja time slot not found with id: " + id));
        
        existing.setSlotTime(slotDTO.getSlotTime());
        existing.setIsActive(slotDTO.getIsActive());
        
        PoojaTimeSlot updated = poojaTimeSlotRepository.save(existing);
        return convertPoojaTimeSlotToDTO(updated);
    }

    @Override
    public void deletePoojaTimeSlot(Long id) {
        if (!poojaTimeSlotRepository.existsById(id)) {
            throw new RuntimeException("Pooja time slot not found with id: " + id);
        }
        poojaTimeSlotRepository.deleteById(id);
    }
    
    @Override
    public List<BookingResponseDTO> getAllPoojaBookings() {
        return poojaBookingRepository.findAll().stream()
                .map(booking -> {
                    BookingResponseDTO dto = new BookingResponseDTO();
                    dto.setId(booking.getId());
                    dto.setUserId(booking.getUser().getId());
                    dto.setDarshanSlotName(booking.getPoojaType().getDisplayName());
                    dto.setSlotTime(booking.getTimeSlot());
                    dto.setBookingDate(booking.getPoojaDate());
                    dto.setNumberOfPeople(1);
                    dto.setTotalAmount(booking.getTotalAmount());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private AdminPoojaTimeSlotDTO convertPoojaTimeSlotToDTO(PoojaTimeSlot entity) {
        return new AdminPoojaTimeSlotDTO(
                entity.getId(),
                entity.getSlotTime(),
                entity.getIsActive()
        );
    }

    private PoojaTimeSlot convertPoojaTimeSlotToEntity(AdminPoojaTimeSlotDTO dto) {
        return new PoojaTimeSlot(
                dto.getId(),
                dto.getSlotTime(),
                dto.getIsActive()
        );
    }
}
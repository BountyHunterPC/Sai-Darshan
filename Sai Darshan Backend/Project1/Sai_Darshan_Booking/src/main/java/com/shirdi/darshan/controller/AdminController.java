package com.shirdi.darshan.controller;

import com.shirdi.darshan.dtos.AdminAartiTypeDTO;
import com.shirdi.darshan.dtos.AdminDarshanSlotDTO;
import com.shirdi.darshan.dtos.AdminDarshanTypeDTO;
import com.shirdi.darshan.dtos.AdminPoojaTypeDTO;
import com.shirdi.darshan.dtos.AdminPoojaTimeSlotDTO;
import com.shirdi.darshan.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    
    private final AdminService adminService;
    
//    DARSHAN APIs
    
    @GetMapping("/darshan")
    public ResponseEntity<?> getAllDarshanTypes() {
        try {
            return ResponseEntity.ok(adminService.getAllDarshanTypes());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/darshan")
    public ResponseEntity<?> createDarshanType(@RequestBody AdminDarshanTypeDTO darshanTypeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(adminService.createDarshanType(darshanTypeDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/darshan/{id}")
    public ResponseEntity<?> updateDarshanType(@PathVariable Long id, 
                                             @RequestBody AdminDarshanTypeDTO darshanTypeDTO) {
        try {
            return ResponseEntity.ok(adminService.updateDarshanType(id, darshanTypeDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/darshan/{id}")
    public ResponseEntity<?> deleteDarshanType(@PathVariable Long id) {
        try {
            adminService.deleteDarshanType(id);
            return ResponseEntity.ok("Darshan type deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/bookings/darshan")
    public ResponseEntity<?> getAllDarshanBookings() {
        try {
            return ResponseEntity.ok(adminService.getAllDarshanBookings());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    
    
//    ADMIN DASHBOARD APIs
    
    @GetMapping("/stats")
    public ResponseEntity<?> getStatistics() {
        try {
            return ResponseEntity.ok(adminService.getStatistics());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(adminService.getAllUsers());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(adminService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    
//    DARSHAN SLOT APIs
    
    @GetMapping("/darshan/slots")
    public ResponseEntity<?> getAllDarshanSlots() {
        try {
            return ResponseEntity.ok(adminService.getAllDarshanSlots());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/darshan/slots")
    public ResponseEntity<?> createDarshanSlot(@RequestBody AdminDarshanSlotDTO slotDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(adminService.createDarshanSlot(slotDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/darshan/slots/{id}")
    public ResponseEntity<?> updateDarshanSlot(@PathVariable Long id, 
                                              @RequestBody AdminDarshanSlotDTO slotDTO) {
        try {
            return ResponseEntity.ok(adminService.updateDarshanSlot(id, slotDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/darshan/slots/{id}")
    public ResponseEntity<?> deleteDarshanSlot(@PathVariable Long id) {
        try {
            adminService.deleteDarshanSlot(id);
            return ResponseEntity.ok("Darshan slot deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    
//    AARTI APIs
    
    @GetMapping("/aarti")
    public ResponseEntity<?> getAllAartiTypes() {
        try {
            return ResponseEntity.ok(adminService.getAllAartiTypes());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/aarti")
    public ResponseEntity<?> createAartiType(@RequestBody AdminAartiTypeDTO aartiTypeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(adminService.createAartiType(aartiTypeDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/aarti/{id}")
    public ResponseEntity<?> updateAartiType(@PathVariable Long id, 
                                           @RequestBody AdminAartiTypeDTO aartiTypeDTO) {
        try {
            return ResponseEntity.ok(adminService.updateAartiType(id, aartiTypeDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/aarti/{id}")
    public ResponseEntity<?> deleteAartiType(@PathVariable Long id) {
        try {
            adminService.deleteAartiType(id);
            return ResponseEntity.ok("Aarti type deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/bookings/aarti")
    public ResponseEntity<?> getAllAartiBookings() {
        try {
            return ResponseEntity.ok(adminService.getAllAartiBookings());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    
//    POOJA APIs
    
    @GetMapping("/pooja")
    public ResponseEntity<?> getAllPoojaTypes() {
        try {
            return ResponseEntity.ok(adminService.getAllPoojaTypes());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/pooja")
    public ResponseEntity<?> createPoojaType(@RequestBody AdminPoojaTypeDTO poojaTypeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(adminService.createPoojaType(poojaTypeDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/pooja/{id}")
    public ResponseEntity<?> updatePoojaType(@PathVariable Long id, 
                                           @RequestBody AdminPoojaTypeDTO poojaTypeDTO) {
        try {
            return ResponseEntity.ok(adminService.updatePoojaType(id, poojaTypeDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/pooja/{id}")
    public ResponseEntity<?> deletePoojaType(@PathVariable Long id) {
        try {
            adminService.deletePoojaType(id);
            return ResponseEntity.ok("Pooja type deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    
//    POOJA TIME SLOT APIs
    
    @GetMapping("/pooja/time-slots")
    public ResponseEntity<?> getAllPoojaTimeSlots() {
        try {
            return ResponseEntity.ok(adminService.getAllPoojaTimeSlots());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/pooja/time-slots")
    public ResponseEntity<?> createPoojaTimeSlot(@RequestBody AdminPoojaTimeSlotDTO slotDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(adminService.createPoojaTimeSlot(slotDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/pooja/time-slots/{id}")
    public ResponseEntity<?> updatePoojaTimeSlot(@PathVariable Long id, 
                                                @RequestBody AdminPoojaTimeSlotDTO slotDTO) {
        try {
            return ResponseEntity.ok(adminService.updatePoojaTimeSlot(id, slotDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/pooja/time-slots/{id}")
    public ResponseEntity<?> deletePoojaTimeSlot(@PathVariable Long id) {
        try {
            adminService.deletePoojaTimeSlot(id);
            return ResponseEntity.ok("Pooja time slot deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/bookings/pooja")
    public ResponseEntity<?> getAllPoojaBookings() {
        try {
            return ResponseEntity.ok(adminService.getAllPoojaBookings());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    
    
}
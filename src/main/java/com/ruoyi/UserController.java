package com.ruoyi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    final private UserRepo userRepository;

    @Autowired
    public UserController(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getUserByAge(){

        Long id =  userRepository.findAll()
                .stream()
                .filter(age -> age.getAge() > 12)
                .min((age1,age2) -> {
                    int ageOne = age1.getAge();
                    int ageTwo = age2.getAge();
                    return ageOne < ageTwo ? 1 : 0;
                })
                .map(Users::getId)
                .orElse(null);

        return ResponseEntity.ok( id != null ? userRepository.findById(id).orElse(null) : null);

    }

    @GetMapping("/save")
    public ResponseEntity<?> saveUsers(){
        List<Users> list = List.of(
                new Users(1L,"Tom", LocalDate.of(2000, Month.MARCH, 1),15),
                new Users(2L,"Jerry", LocalDate.of(2001, Month.APRIL, 12),18),
                new Users(3L,"Backed", LocalDate.of(2003, Month.MARCH, 4),16),
                new Users(4L,"Scj", LocalDate.of(2006, Month.MARCH, 4),12)
        );
        return ResponseEntity.ok(userRepository.saveAll(list));
    }

}

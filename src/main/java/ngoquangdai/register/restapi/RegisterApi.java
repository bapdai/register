package ngoquangdai.register.restapi;

import ngoquangdai.register.entity.Register;
import ngoquangdai.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("project/api/register")
public class RegisterApi {
    // CURD
    @Autowired
    RegisterService registerService;
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Register>> getList(){
        return ResponseEntity.ok(registerService.findAll());
    }
    //
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<Register> optionalPerson = registerService.findById(id);
        if (!optionalPerson.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalPerson.get());
    }
    //
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Register> create(@RequestBody Register register) {
        return ResponseEntity.ok(registerService.save(register));
    }

    //    Sua thong tin(U)
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Register> update(@PathVariable Integer id, @RequestBody Register register) {
        Optional<Register> optionalRegister = registerService.findById(id);
        if (!optionalRegister.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Register exitsRegister = optionalRegister.get();
        //     map object
        exitsRegister.setId(register.getId());
        exitsRegister.setFullname(register.getFullname());
        exitsRegister.setEmail(register.getEmail());
        exitsRegister.setPhone(register.getPhone());
        exitsRegister.setBirthday(register.getBirthday());
        exitsRegister.setAddress(register.getAddress());
        exitsRegister.setLink(register.getLink());
        return ResponseEntity.ok(registerService.save(exitsRegister));
    }
    //        Xoa thong tin
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!registerService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        registerService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

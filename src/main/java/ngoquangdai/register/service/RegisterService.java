package ngoquangdai.register.service;

import ngoquangdai.register.entity.Register;
import ngoquangdai.register.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;
    public List<Register> findAll(){
        return registerRepository.findAll();
    }
    public Optional<Register> findById(Integer id){
        return registerRepository.findById(id);
    }
    public Register save(Register register){
        return registerRepository.save(register);
    }
    public void deleteById(Integer id){
        registerRepository.deleteById(id);
    }
}

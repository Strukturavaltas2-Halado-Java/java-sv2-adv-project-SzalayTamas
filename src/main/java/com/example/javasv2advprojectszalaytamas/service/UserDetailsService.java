package com.example.javasv2advprojectszalaytamas.service;

import com.example.javasv2advprojectszalaytamas.command.create.CreateUserCommand;
import com.example.javasv2advprojectszalaytamas.dto.UserDto;
import com.example.javasv2advprojectszalaytamas.entity.Measurement;
import com.example.javasv2advprojectszalaytamas.entity.Meter;
import com.example.javasv2advprojectszalaytamas.entity.User;
import com.example.javasv2advprojectszalaytamas.mapper.MapperToDto;
import com.example.javasv2advprojectszalaytamas.repositori.InvoiceRepository;
import com.example.javasv2advprojectszalaytamas.repositori.MeterRepository;
import com.example.javasv2advprojectszalaytamas.repositori.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class UserDetailsService {
    private UserRepository userRepository;
    private MeterRepository meterRepository;
    private InvoiceRepository invoiceRepository;
    private MapperToDto mapper;

    public UserDto saveUser(CreateUserCommand command) {
        User user = new User();
        user.setContact(mapper.createContact(command));
        return mapper.toUserDto(userRepository.save(user));
    }

    public UserDto saveUserWithMeter(CreateUserCommand command) {
        User user = new User();
        user.setContact(mapper.createContact(command));
        Meter meter = new Meter();
        meter.addMeasurement(new Measurement(LocalDateTime.now(), 0));
        user.addMeter(meter);
        return mapper.toUserDto(userRepository.save(user));
    }

    public List<UserDto> findAllUser() {
        return mapper.toUserDto(userRepository.findAll());
    }

    @Transactional
    public void addMeterToUserByUserId(Meter meter, Long id) {
        User user = userRepository.findByIdWithMeter(id);
        user.addMeter(meter);
        meterRepository.save(meter);
    }


}

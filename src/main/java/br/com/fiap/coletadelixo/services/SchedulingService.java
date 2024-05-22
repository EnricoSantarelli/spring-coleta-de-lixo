package br.com.fiap.coletadelixo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.coletadelixo.enums.Status;
import br.com.fiap.coletadelixo.models.Scheduling;
import br.com.fiap.coletadelixo.repositories.SchedulingRepository;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    public Scheduling createScheduling(Scheduling scheduling) {
        return schedulingRepository.save(scheduling);
    }

    public Scheduling getSchedulingById(Long id, Long userId) {
        Scheduling schedulingOptional = schedulingRepository.findByIdAndUserId(id, userId);

        if (schedulingOptional != null) {
            return schedulingOptional;
        } else {
            throw new RuntimeException("Agendamento não encontrado");
        }
    }

    public void deleteScheduling(Long id, Long userId) {
        Scheduling schedulingOptional = schedulingRepository.findByIdAndUserId(id, userId);

        if (schedulingOptional != null) {
            schedulingRepository.delete(schedulingOptional);
        } else {
            throw new RuntimeException("Agendamento não encontrado");
        }
    }

    public Scheduling updateScheduling(Scheduling scheduling) {
        Scheduling schedulingReturned = schedulingRepository.findByIdAndUserId(scheduling.getId(),
                scheduling.getUser().getId());

        if (schedulingReturned != null) {
            schedulingReturned.setTimestamp(scheduling.getTimestamp());
            schedulingReturned.setStatus(scheduling.getStatus());
            return schedulingRepository.save(schedulingReturned);
        } else {
            throw new RuntimeException("Agendamento não encontrado");
        }
    }

    public Scheduling confirmScheduling(Long id) {
        Optional<Scheduling> schedulingOptional = schedulingRepository.findById(id);

        if (schedulingOptional.isPresent()) {
            schedulingOptional.get().setStatus(Status.CONFIRMED);
            return schedulingRepository.save(schedulingOptional.get());
        } else {
            throw new RuntimeException("Agendamento não encontrado");
        }
    }

}

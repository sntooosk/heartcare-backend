package com.etec.backend.services.impl;

import com.etec.backend.dtos.MedicationResponseDTO;
import com.etec.backend.entities.Medication;
import com.etec.backend.dtos.ResponseDTO;
import com.etec.backend.repositories.MedicationRepository;
import com.etec.backend.services.MedicationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Override
    public List<MedicationResponseDTO> findByUserId(Long userId) {
        List<Medication> medications = medicationRepository.findByUserId(userId);
        return medications.stream()
                .map(medication -> new MedicationResponseDTO(
                        medication.getId(),
                        medication.getName(),
                        medication.getDosage(),
                        medication.getTimeMedication()))
                .collect(Collectors.toList());
    }

    @Override
    public Object create(Medication medication) {
        try {
            Medication savedMedication = medicationRepository.save(medication);
            return new ResponseDTO("OK", "Medicamento criado com sucesso: " + savedMedication.getId());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao criar medicamento: " + e.getMessage());
        }
    }

    @Override
    public Object update(Long id, Medication medication) {
        try {
            Optional<Medication> existingMedicationOpt = medicationRepository.findById(id);
            if (!existingMedicationOpt.isPresent()) {
                return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
            }
            medication.setId(id);
            Medication updatedMedication = medicationRepository.save(medication);
            return new ResponseDTO("OK", "Medicamento atualizado com sucesso: " + updatedMedication.getId());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao atualizar medicamento: " + e.getMessage());
        }
    }

    @Override
    public Object delete(Long id) {
        try {
            if (!medicationRepository.existsById(id)) {
                return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
            }
            medicationRepository.deleteById(id);
            return new ResponseDTO("OK", "Medicamento removido com sucesso.");
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao excluir medicamento: " + e.getMessage());
        }
    }
}

package com.etec.backend.services;

import com.etec.backend.dtos.MedicationConcatResponseDTO;
import com.etec.backend.dtos.MedicationResponseDTO;
import com.etec.backend.dtos.PressureConcatResponseDTO;
import com.etec.backend.entities.Medication;
import com.etec.backend.entities.Pressure;

import java.util.List;

public interface MedicationService {

    List<MedicationConcatResponseDTO> getAll();

    List<MedicationResponseDTO> findByUserId(Long userId);

    Object create(Medication medication);

    Object update(Long id, Medication medication);

    Object delete(Long id);
}

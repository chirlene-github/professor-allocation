package com.project.professor.allocation.controller.mapper;

import com.project.professor.allocation.dto.AllocationCompleteDTO;
import com.project.professor.allocation.dto.AllocationCreationDTO;
import com.project.professor.allocation.dto.AllocationSimpleDTO;
import com.project.professor.allocation.entity.Allocation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllocationMapper {

    private final ModelMapper modelMapper;

    public AllocationMapper() {
        this.modelMapper = new ModelMapper();
    }

    public List<AllocationSimpleDTO> toAllocationSimpleDTO(List<Allocation> allocations) {
        return allocations.stream().map(this::toAllocationSimpleDTO).collect(Collectors.toList());
    }

    public AllocationSimpleDTO toAllocationSimpleDTO(Allocation allocation) {
        return modelMapper.map(allocation, AllocationSimpleDTO.class);
    }

    public AllocationCompleteDTO toAllocationCompleteDTO(Allocation allocation) {
        return modelMapper.map(allocation, AllocationCompleteDTO.class);
    }

    public Allocation toAllocation(AllocationCreationDTO allocationCreationDTO) {
        return modelMapper.map(allocationCreationDTO, Allocation.class);
    }
}

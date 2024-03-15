package dev.akinaksoy.rentacar.business.concretes;

import dev.akinaksoy.rentacar.business.abstracts.FuelService;
import dev.akinaksoy.rentacar.business.dtos.requests.fuel.CreateFuelRequest;
import dev.akinaksoy.rentacar.business.dtos.responses.fuel.CreatedFuelResponse;
import dev.akinaksoy.rentacar.core.utilities.mapping.ModelMapperService;
import dev.akinaksoy.rentacar.dataaccess.abstracts.FuelRepository;
import dev.akinaksoy.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;
    private ModelMapperService modelMapperService;
    @Override
    public CreatedFuelResponse createFuel(
            CreateFuelRequest request
    ) {
        Fuel fuel = this.modelMapperService
                .forRequest()
                .map(request,Fuel.class);

        fuel.setCreatedDate(LocalDateTime.now());

        fuelRepository.save(fuel);

        CreatedFuelResponse response = this.modelMapperService
                .forResponse().map(fuel, CreatedFuelResponse.class);

        return response;
    }
}
package dev.akinaksoy.rentacar.business.abstracts;



import dev.akinaksoy.rentacar.business.dtos.requests.car.CreateCarRequest;
import dev.akinaksoy.rentacar.business.dtos.responses.car.CreatedCarResponse;
import dev.akinaksoy.rentacar.business.dtos.responses.car.GetAllCarResponse;

import java.util.List;

public interface CarService {
    CreatedCarResponse createCar(
            CreateCarRequest request
    );
    List<GetAllCarResponse> getAllCars(

    );
}

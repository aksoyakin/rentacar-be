package dev.akinaksoy.rentacar.api.controllers;

import dev.akinaksoy.rentacar.business.abstracts.ModelService;
import dev.akinaksoy.rentacar.business.dtos.requests.model.CreateModelRequest;
import dev.akinaksoy.rentacar.business.dtos.requests.model.UpdateModelRequest;
import dev.akinaksoy.rentacar.business.dtos.responses.model.CreatedModelResponse;
import dev.akinaksoy.rentacar.business.dtos.responses.model.GetAllModelResponse;
import dev.akinaksoy.rentacar.business.dtos.responses.model.GetModelByIdResponse;
import dev.akinaksoy.rentacar.business.dtos.responses.model.UpdateModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelController {
    private ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse createModel(
            @Valid @RequestBody CreateModelRequest request
    ){
        return modelService.createModel(request);
    }

    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllModelResponse> getAllModels(

    ){
        return modelService.getAllModels();
    }
    @GetMapping("/{id}")
    public GetModelByIdResponse getModelById(
            @PathVariable int id
    ){
        return modelService.getModelById(id);
    }
    @PutMapping("/{id}")
    public UpdateModelResponse updateModelById(
            @RequestBody UpdateModelRequest request,
            @PathVariable int id
    ) {
        return modelService.updateModelById(request,id);
    }
    @DeleteMapping("/{id}")
    public void deleteModelById(
            @PathVariable int id
    ){
        modelService.deleteModelById(id);
    }
}

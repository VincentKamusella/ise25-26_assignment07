package de.seuhd.campuscoffee.api.controller;

import de.seuhd.campuscoffee.api.dtos.ReviewDto;
import de.seuhd.campuscoffee.api.mapper.DtoMapper;
import de.seuhd.campuscoffee.api.openapi.CrudOperation;
import de.seuhd.campuscoffee.domain.model.objects.Review;
import de.seuhd.campuscoffee.domain.ports.api.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.catalina.connector.Response;

import de.seuhd.campuscoffee.api.mapper.ReviewDtoMapper;
import static de.seuhd.campuscoffee.api.openapi.Operation.*;
import static de.seuhd.campuscoffee.api.openapi.Resource.REVIEW;
import de.seuhd.campuscoffee.domain.ports.api.ReviewService;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * Controller for handling reviews for POS, authored by users.
 */
@Tag(name="Reviews", description="Operations for managing reviews for points of sale.")
@Controller
@RequestMapping("/api/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController extends CrudController<Review, ReviewDto, Long> {
    private final ReviewService reviewService;
    private final ReviewDtoMapper reviewDtoMapper;

    // TODO: Correctly implement the service() and mapper() methods. Note the IntelliJ warning resulting from the @NonNull annotation.

    @Override
    protected @NonNull CrudService<Review, Long> service() {
        return reviewService;
    }

    @Override
    protected @NonNull DtoMapper<Review, ReviewDto> mapper() {
        return reviewDtoMapper;
    }

    @Operation
    @CrudOperation(operation=GET_ALL, resource=REVIEW)
    @GetMapping("")
    public @NonNull ResponseEntity<List<ReviewDto>> getAll() {
        return super.getAll();
    }

    @Operation
    @CrudOperation(operation=getById, resource=Review)
    @GetMapping("")
    public @NonNull ResponseEntity<ReviewDto> getById(
        @Parameter(description="Unique identifier of the Review to retrieve.", required=true)
        @PathVariable Long id) {
        return super.getById(id);
    }
}

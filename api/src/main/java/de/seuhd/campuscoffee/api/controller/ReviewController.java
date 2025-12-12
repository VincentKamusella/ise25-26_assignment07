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
import org.yaml.snakeyaml.events.Event;

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
    @CrudOperation(operation=GET_BY_ID, resource=REVIEW)
    @GetMapping("")
    public @NonNull ResponseEntity<ReviewDto> getById(
        @Parameter(description="Unique identifier of the Review to retrieve.", required=true)
        @PathVariable Long id) {
        return super.getById(id);
    }
    @Operation
    @CrudOperation(operation=CREATE, resource=REVIEW)
    @GetMapping("")
    public @NonNull ResponseEntity <ReviewDto> create (Dto dto) {
        Dto created = upsert(dto);
        return ResponseEntity
            .created(getLocation(created.getId()))
            .body(created);
    }
    @Operation
    @CrudOperation(operation=UPDATE, resource=REVIEW)
    @GetMapping("")
    public @NonNull ResponseEntity <ReviewDto> update (ID id, Dto dto) {
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("ID in path and body do not match.");
        }
        return ResponseEntity.ok(
                upsert(dto)
        );
    }
    @Operation
    @CrudOperation(operation=DELETE, resource=REVIEW)
    @GetMapping("")
    public @NonNull ResponseEntity <Void> delete (ID id) {
    service().delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation
    @CrudOperation(operation=FILTER, resource=REVIEW)
    @GetMapping("")
    public @NonNull ResponseEntity <ReviewDto> filter (
        @RequestParam("pos_id") Long posId,
        @RequestParam("approved") boolean approved
    ) {
    return ResponseEntity.ok(
                reviewDtoMapper.fromDomain(reviewService.getById(posId))
                );
    }
    public ResponseEntity <ReviewDto> approve (
    @PathVariable Long id,
    @RequestParam("user_id") Long userId
    ) {
        return ResponseEntity.ok(
            reviewDtoMapper.fromDomain(reviewService.getById(userId))
        );
    }
}

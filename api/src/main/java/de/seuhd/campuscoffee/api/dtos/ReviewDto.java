package de.seuhd.campuscoffee.api.dtos;

import java.time.LocalDateTime;

import org.jspecify.annotations.Nullable;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * DTO record for POS metadata.
 */
@Builder(toBuilder = true)
public record ReviewDto (
    @Nullable Long id, //can be Null when a new review is created

    @Nullable LocalDateTime createdAt, //can be Null when a new review is created

    @Nullable LocalDateTime updatedAt, //can be Null when a new review is created

    @Nonnull Long posId, //cannot be null

    @Nonnull Long authorId, //cannot be null

    @NotBlank String review, //cannnot be null or empty

    @Nullable Boolean approved //set in Responses, can be non existant





) implements Dto<Long> {
    @Override
    public @Nullable Long getId() {
        return id;
    }
}

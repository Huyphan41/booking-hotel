package com.example.demo.infrastructure.adapters.output.persistence.specification;

import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelEntity;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HotelSpecification {

  public static Specification<HotelEntity> hotelSearchSpec(HotelFilterSpec filters) {
    return (root, query, cb) -> allOf(cb, createPredicates(root, cb, filters));
  }

  private static Predicate[] createPredicates(Root<HotelEntity> root, CriteriaBuilder cb, HotelFilterSpec filters) {
    return new Predicate[] {
        icontains(cb, root.get(HotelEntity_.destination), filters.getDestination())
    };
  }

  public static Predicate allOf(CriteriaBuilder cb, Predicate... predicates) {
    List<Predicate> validPredicates = (List) Stream.of(predicates).filter(Objects::nonNull).collect(Collectors.toList());
    return cb.and(validPredicates.toArray(new Predicate[0]));
  }

  public static Predicate icontains(CriteriaBuilder cb, Path path, String value) {
    return !StringUtils.hasText(value) ? null : cb.like(cb.lower(path), appendLikeExpression(value.toLowerCase()));
  }

  public static String appendLikeExpression(String value) {
    return "%" + escapeSpecialCharacters(value) + "%";
  }

  private static String escapeSpecialCharacters(String input) {
    final String[] speChars = {"\\", "%", "_"};

    for (String speChar : speChars) {
      if (!input.contains(speChar)) {
        continue;
      }
      input = input.replace(speChar, "\\" + speChar);
    }

    return input;
  }
}

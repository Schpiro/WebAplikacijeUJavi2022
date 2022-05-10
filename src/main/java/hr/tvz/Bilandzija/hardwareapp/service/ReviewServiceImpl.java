package hr.tvz.Bilandzija.hardwareapp.service;

import hr.tvz.Bilandzija.hardwareapp.model.dto.ReviewDTO;
import hr.tvz.Bilandzija.hardwareapp.model.pojo.Review;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.ReviewRepository;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByHardwareCode(Integer code) {
        return reviewRepository.findAllByHardwareCode(code).stream().map(this::mapReviewToDTO).collect(Collectors.toList());

    }

    private ReviewDTO mapReviewToDTO(final Review review){
        return new ReviewDTO(review.getTitle(),review.getText(),review.getGrade());
    }
}

package hr.tvz.Bilandzija.hardwareapp.controller;

import hr.tvz.Bilandzija.hardwareapp.model.dto.ReviewDTO;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping("/{Id}")
    public List<ReviewDTO> getAllReviewByHardwareCode(@PathVariable final Integer Id){return reviewService.findByHardwareId(Id);}
}

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

    @GetMapping(params = "hardwareCode")
    public List<ReviewDTO> getAllReviewByHardwareCode(@RequestParam final Integer hardwareCode){return reviewService.findAllByHardwareCode(hardwareCode);}
}

package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.service.SessionCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class SessionCartRestController {

    private final SessionCartService cartService;

    @GetMapping("/getCart")
    public List<Long> displayAllCart(){
        return cartService.getCartEntries();
    }

    @GetMapping("/addMovie/{movieId}")
    public List<Long> addMovieToCart(@PathVariable Long movieId){
        return cartService.addMovie(movieId);
    }

    @GetMapping("/removeMovie/{movieId}")
    public void removeMovieFromCart(@PathVariable Long movieId){
        cartService.removeMovie(movieId);
    }

    @GetMapping("/cartToOrder")
    public MoviesOrder addCartToOrder(){
        return cartService.toOrder();
    }




}

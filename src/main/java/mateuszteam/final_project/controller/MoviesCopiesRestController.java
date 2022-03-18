package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieCopyDto;
import mateuszteam.final_project.service.MoviesCopiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/copies")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class MoviesCopiesRestController {

    private final MoviesCopiesService copiesService;

    @GetMapping("/{id}")
    MovieCopyDto get(@PathVariable Long id){
        return copiesService.get(id);
    }

    @GetMapping("/movies/{id}")
    List<MovieCopyDto> displayCopiesForSingleMovie(@PathVariable Long id){
      return copiesService.findAllCopiesForSingleMovie(id);
    }

    @PostMapping("/movie/{id}")
    MovieCopyDto addNewCopy(@PathVariable Long id) {
        return copiesService.create(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/remove/{id}")
    public void removeCopyByCopyId(@PathVariable Long id){
        copiesService.removeCopy(id);
    }


}

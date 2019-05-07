package br.com.mballoni;

import br.com.mballoni.toggleboot.ToggleService;
import br.com.mballoni.toggleboot.exception.ToggleUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private ToggleService toggleService;

    @GetMapping("/one")
    public String toggleOn() throws ToggleUnavailableException {
        return toggleService.isEnabled("my-toggle") ? "On!" : "Off :(";
    }

    @GetMapping("/two")
    public String toggleOff() throws ToggleUnavailableException {
        return toggleService.isEnabled("my-other-toggle") ? "On!" : "Off :(";
    }
}

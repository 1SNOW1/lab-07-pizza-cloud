package com.cydeo.controller;

import com.cydeo.model.Pizza;
import com.cydeo.model.PizzaOrder;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) throws Exception {

        PizzaOrder pizzaOrder = new PizzaOrder();

        // TODO fix the getPizza() method below in line 46.
        pizzaOrder.setPizza(getPizza(pizzaId)); //so here we are setting and getting the pizzaId of the pizza we chose to order, jumping to line 39

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }

    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable UUID pizzaId, @ModelAttribute PizzaOrder pizzaOrder) throws Exception {

        // Saving the order
        pizzaOrder.setPizza(getPizza(pizzaId)); // the reason why we are resetting and getting the pizzaId here after submitting the pizza is to not lose the pizza id.
        System.out.println("Order is successfully saved");

        return "redirect:/home";
    }

    //TODO complete method
    private Pizza getPizza(UUID pizzaId) throws Exception {
        // Get the pizza from repository based on it's id
      return  pizzaRepository.readAll().stream().filter(p -> p.getId().equals(pizzaId)).findFirst().orElseThrow(() -> new Exception("Pizza not found"));

    }

}

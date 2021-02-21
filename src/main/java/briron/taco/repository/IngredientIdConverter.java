package briron.taco.repository;

import briron.taco.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}

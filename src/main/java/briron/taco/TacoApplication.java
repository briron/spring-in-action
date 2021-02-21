package briron.taco;

import briron.taco.domain.Ingredient;
import briron.taco.domain.Ingredient.Type;
import briron.taco.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TacoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
				repository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
				repository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				repository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
				repository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				repository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
				repository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
				repository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
				repository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
				repository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
			}
		};
	}

}

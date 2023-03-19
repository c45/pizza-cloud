package pizza;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@Entity
public class Ingredient {

    @Id
    private String id;

    private String name;
    private Type type;

    public enum Type {
        DOUGH, VEGETABLES, SAUCES, CHEESE, MEAT
    }
}

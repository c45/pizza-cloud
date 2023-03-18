package pizza;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    @Id
    private String id;

    private String name;
    private Type type;

    public enum Type {
        DOUGH, VEGETABLES, SAUCES, CHEESE, MEAT
    }
}

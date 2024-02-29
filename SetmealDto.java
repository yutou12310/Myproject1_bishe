package izhen_start.dto;


import izhen_start.entity.Setmeal;
import izhen_start.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

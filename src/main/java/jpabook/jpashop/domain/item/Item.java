package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import jpabook.jpashop.domain.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import jpabook.jpashop.exception.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    /**
      stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0){
            throw new NotEnoughStockException("neeed more stock");
        }

        this.stockQuantity = restStock;
    }
}

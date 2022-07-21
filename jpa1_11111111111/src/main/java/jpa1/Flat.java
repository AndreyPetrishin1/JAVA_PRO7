package jpa1;

import javax.persistence.*;

@Entity
@Table(name="FLAT")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String square;

    @Column(nullable = false)
    private int quantityRoom;

    @Column(nullable = false)
    private long price;

    public Flat() {
    }

    public Flat(String area, String square, int quantityRoom, long price) {
        this.area = area;
        this.square = square;
        this.quantityRoom = quantityRoom;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public int getQuantityRoom() {
        return quantityRoom;
    }

    public void setQuantityRoom(int quantityRoom) {
        this.quantityRoom = quantityRoom;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", square='" + square + '\'' +
                ", quantityRoom='" + quantityRoom + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

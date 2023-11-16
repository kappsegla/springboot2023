package com.example.springboot23;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.hibernate.proxy.HibernateProxy;


import java.util.Objects;

@Entity
public class Playground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonSerialize(using = Point2DSerializer.class)
    private Point<G2D> coordinate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point<G2D> coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Playground that = (Playground) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

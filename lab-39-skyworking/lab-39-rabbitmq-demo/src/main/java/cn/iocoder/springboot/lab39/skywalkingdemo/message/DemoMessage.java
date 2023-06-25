package cn.iocoder.springboot.lab39.skywalkingdemo.message;

import java.io.Serializable;

public class DemoMessage implements Serializable {

    /**
     * 编号
     */
    private Long id;

    public DemoMessage setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "id=" + id +
                '}';
    }

}

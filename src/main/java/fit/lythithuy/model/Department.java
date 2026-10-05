/*
 * @ (#) Department        1.0     10/6/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */

package fit.lythithuy.model;

/*
 * @description:
 * @author: Thuy, Ly Thi
 * @version: 1.0
 * @created: 10/6/2026  3:49 AM
 */
public class Department {
    private int id;
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


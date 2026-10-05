/*
 * @ (#) Position        1.0     10/6/2026
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
public class Position {
    private int id;
    private String title;

    // Constructors
    public Position() {
    }

    public Position(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


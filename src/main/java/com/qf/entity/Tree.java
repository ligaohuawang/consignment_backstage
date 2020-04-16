package com.qf.entity;

public class Tree {
    private Integer id;
    private String name;
    private Integer pid;
    private boolean checked;//要不要选中，需要看当前的角色是否有相应的菜单

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", checked=" + checked +
                '}';
    }
}

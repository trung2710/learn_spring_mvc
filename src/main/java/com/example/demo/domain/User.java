package com.example.demo.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    // @NotEmpty(message = "Email cannot be empty")
    private String email;   

    @NotNull
    @Size(min=3, message="Password phai co toi thieu 3 ki tu")
    // @StrongPassword(message="password phai co 8 ki tu")
    private String password;
    @NotNull
    @Size(min=3, message="fullname phai cho it nhat 3 ki tu")
    private String fullName;
    private String address;
    // @NotNull(message="vui long nhap so dien thoai")
    // @Size(min=7, message="Vui long nhap day du so dien thoai")
    private String phone;
    private String avatar;
    private String resetPasswordToken;
    
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    //role_id
    //nhiều user có cùng 1 vai trò(role)
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
//cascade = CascadeType.ALL: Khi xóa User, tất cả Order và Cart liên quan sẽ tự động bị xóa.
//orphanRemoval = true: Đảm bảo rằng nếu một Order hoặc Cart không còn tham chiếu đến User, nó sẽ bị xóa.
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne(mappedBy="user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
                + ", address=" + address + ", phone=" + phone + ", avatar=" + avatar + "]";
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

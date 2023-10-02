package com.Ricepify.Models;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;
@Entity
public class SiteUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    private List<Recipe> recipe;
    public List<Recipe>getRecipe()
    {
        return recipe;
    }

    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    private List<RecipeInteraction> recipeInteractions;
    public List<RecipeInteraction> getRecipeInteractions() {
        return recipeInteractions;
    }

    public SiteUser(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public SiteUser() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


















// implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String username;
//    private String password;
//    private String firstName;
//    private String lastName;
//    private String email;
//    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
//    private List<Recipe> recipe;
//    public List<Recipe>getRecipe()
//    {
//        return recipe;
//    }
//
//    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
//    private List<RecipeInteraction> recipeInteractions;
//    public List<RecipeInteraction> getRecipeInteractions() {
//        return recipeInteractions;
//    }
//    public SiteUser() {
//    }
//
//    public SiteUser(String username, String firstName, String lastName, String email, String password) {
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        firstName = firstName;
//    }
//
//    public void setRecipe(List<Recipe> recipe) {
//        this.recipe = recipe;
//    }
//
//    public void setRecipeInteractions(List<RecipeInteraction> recipeInteractions) {
//        this.recipeInteractions = recipeInteractions;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        email = email;
//    }
//
//
//    public void setPassword(String password) {
//        password = password;
//    }
//}

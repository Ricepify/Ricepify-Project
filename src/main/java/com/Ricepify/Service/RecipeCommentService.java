package com.Ricepify.Service;

public interface RecipeCommentService {

    void addComment(String comment, Long recipeId, String username) throws Exception;
}

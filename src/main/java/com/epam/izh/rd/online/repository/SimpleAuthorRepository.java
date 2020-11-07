package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];


    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(),author.getLastName())!=null){
            return false;
        }
        Author[] bufer = new Author[count()+1];
        System.arraycopy(authors,0,bufer,0,count());
        bufer[count()] = author;
        this.authors = bufer;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author: this.authors){
            if (author.getName().equals(name)&&author.getLastName().equals(lastname)){
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        for (int i = 0;i<count();i++){
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())){
                Author[] bufer = new Author[count()-1];
                System.arraycopy(authors,0,bufer,0,i);
                System.arraycopy(authors,i+1,bufer,i,count()-i-1);
                this.authors = bufer;
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return this.authors.length;
    }
}

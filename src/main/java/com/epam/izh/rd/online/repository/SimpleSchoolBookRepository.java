package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] bufer = new SchoolBook[count()+1];
        System.arraycopy(schoolBooks,0,bufer,0,count());
        bufer[count()] = book;
        schoolBooks = bufer;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] returnBooks = new SchoolBook[0];
        for (SchoolBook book: this.schoolBooks){
            if (book.getName().equals(name)){
                SchoolBook[] bufer = new SchoolBook[returnBooks.length+1];
                System.arraycopy(returnBooks,0,bufer,0,returnBooks.length);
                bufer[returnBooks.length] = book;
                returnBooks = bufer;
            }
        }
        return returnBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int check = 0;
        for (int i = 0;i<count();i++){
            System.out.println(i);
            if (schoolBooks[i].getName().equals(name)){
                SchoolBook[] bufer = new SchoolBook[count()-1];
                System.arraycopy(schoolBooks,0,bufer,0,i);
                System.arraycopy(schoolBooks,i+1,bufer,i,count()-i-1);
                this.schoolBooks = bufer;
                i=i-1;
                check++;
            }
        }
        return check > 0;
    }

    @Override
    public int count() {
        return this.schoolBooks.length;
    }
}

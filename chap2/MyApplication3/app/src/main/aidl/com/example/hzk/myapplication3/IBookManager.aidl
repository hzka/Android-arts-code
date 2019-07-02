// IBookManager.aidl
package com.example.hzk.myapplication3;

// Declare any non-default types here with import statements
//我们定义的接口，里面有两个方法：getBookList和addBook。getBook用于远程鼓舞段获取图书列表；addBook用于
//往图书列表中添加一本书。仍然需要导入Boook类，虽然在同一个包里。
import com.example.hzk.myapplication3.Book;

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookLists();
    void addBook(in Book book);
}

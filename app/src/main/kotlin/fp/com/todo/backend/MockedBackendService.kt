package fp.com.todo.backend


import retrofit.client.Response
import retrofit.http.Body
import retrofit.mime.TypedString
import rx.Observable
import rx.lang.kotlin.observable
import java.util.ArrayList
import java.util.HashMap

public class MockedBackendService : Backend {

    private val mockedImages1: ArrayList<String> = ArrayList()
    private val mockedImages2: ArrayList<String> = ArrayList()
    private val tasks: HashMap<Int, Task> = HashMap()
    private var randomizer: Int = 0

//        mockedImages1.add("http://blog.boostability.com/wp-content/uploads/2014/09/Panda-Update.jpg")
//        mockedImages1.add("http://i2.cdn.turner.com/cnnnext/dam/assets/111017060721-giant-panda-bamboo-story-top.jpg")
//        mockedImages1.add("http://inadawords.com/wp-content/uploads/2007/10/panda.jpg")
//        mockedImages1.add("http://media1.s-nbcnews.com/j/streams/2013/September/130923/4B9132043-tdy-130923-panda-baby-03.blocks_desktop_medium.jpg")
//        mockedImages1.add("http://universitypost.dk/files/universitetsavisen.dk/imagecache/930x/pictures/panda.JPG")
//
//        mockedImages2.add("http://thelapine.ca/wp-content/uploads/Penguins.jpg")
//        mockedImages2.add("http://www.barransclass.com/phys1090/circus/Haynes_K/penguin.jpg")
//        mockedImages2.add("http://az598155.vo.msecnd.net/wp-uploads/2014/07/penguinscool.jpg")
//        mockedImages2.add("http://rs1img.memecdn.com/penguins_o_2122957.jpg")
//        mockedImages2.add("http://images.nationalgeographic.com/wpf/media-live/photos/000/247/cache/rock-hopper-penguin-close-up_24706_600x450.jpg")
//
//        tasks.put(0, Task(0, "Task #0", false, mockedImages1.get(0)))
//        tasks.put(1, Task(1, "Task #1", false, mockedImages1.get(1)))
//        tasks.put(2, Task(2, "Task #2", true, mockedImages1.get(2)))
//        tasks.put(3, Task(3, "Task #3", false, mockedImages1.get(3)))
//        tasks.put(4, Task(4, "Task #4", false, mockedImages1.get(4)))
//        tasks.put(5, Task(5, "Task #5", false, mockedImages2.get(0)))
//        tasks.put(6, Task(6, "Task #6", false, mockedImages2.get(1)))
//        tasks.put(7, Task(7, "Task #7", false, mockedImages2.get(2)))
//        tasks.put(8, Task(8, "Task #8", false, mockedImages2.get(3)))
//        tasks.put(9, Task(9, "Task #9", false, mockedImages2.get(4)))
//        tasks.put(10, Task(10, "Task #10", false, ""))
//        tasks.put(11, Task(11, "Task #11", false, ""))
//        tasks.put(12, Task(12, "Task #12", false, ""))
}

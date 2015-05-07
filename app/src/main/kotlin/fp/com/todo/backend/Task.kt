package fp.com.todo.backend

import java.io.Serializable

public data class Task(var id: Int, var name: String, var isDone: Boolean, var imageUrl: String) : Serializable {
}
/*
 In-memory Filesystem
 Scott Rice
*/

package filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private String data;
    private List<Directory> children;
    private Directory parent;

    public Directory(String data) {
        this.data = data;
        this.children = new ArrayList<Directory>();
    }

    public Directory getParent() {
        return this.parent;
    }

    public List<Directory> getChildren() {
        return this.children;
    }

    public void addChild(Directory child) {
        child.parent = this;
        children.add(child);
    }

    public void removeChild(Directory child) throws IndexOutOfBoundsException {
        children.remove(child);
    }

    public String getData() {
        return this.data;
    }
}


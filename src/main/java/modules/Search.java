package modules;

public class Search implements SearchModule{

    /**
     * Search constructor
     */
    public Search(){

    }

    /**
     * find the first occurrence of the given query in the text
     * @param query word want to find
     * @param text targeted text
     * @return the index of the first occurrence of the query, return -1 if not find
     */
    @Override
    public int find(String query, String text) {
        query = query.toLowerCase();
        text = text.toLowerCase();
        return text.indexOf(query);
    }
}

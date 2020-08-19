import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ParallelReadFilter {
    /**
     *Read txt file with charset UTF_8
     * @param file
     * @return an Optional of list of string
     */
    public static Optional<List<String>> readFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> collect = bufferedReader.lines().collect(Collectors.toList());
            return Optional.of(collect);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    /**
     * Read files from the file list by using Parallel stream and collect only the strings that begin with the specific letter in a HashSet
     * @param files, list of files
     * @param filterByLetter
     * @return a Hashset
     */
    public Set<String> parallelReadAndFilter(List<File> files, String filterByLetter) {
        List<String> stringList = files.parallelStream()
                .flatMap(file -> ParallelReadFilter.readFile(file).get().stream())
                .filter(s -> s.startsWith(filterByLetter))
                .collect(Collectors.toList());

        Set<String> resultSet = new HashSet<>(stringList);
        System.out.println("The size of resultSet is: " + resultSet.size());
        resultSet.forEach(rs -> System.out.println(rs));

        return resultSet;
    }
}

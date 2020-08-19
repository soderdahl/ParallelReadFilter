import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ParallelReadFilterTest {

    @org.junit.jupiter.api.Test
    void readFile() {
        String filePath = this.getClass().getClassLoader().getResource("utf8list1.txt").getFile();
        File file = new File(filePath);
        Optional<List<String>> lines = ParallelReadFilter.readFile(file);
        assertNotNull(lines);
    }

    @org.junit.jupiter.api.Test
    void parallelReadAndFilter() {
        ParallelReadFilter parallelReadFilter = new ParallelReadFilter();
        String filePath1 = this.getClass().getClassLoader().getResource("utf8list1.txt").getFile();
        String filePath2 = this.getClass().getClassLoader().getResource("utf8list7.txt").getFile();
        String filePath3 = this.getClass().getClassLoader().getResource("utf8list8.txt").getFile();
        String filePath4 = this.getClass().getClassLoader().getResource("utf8list9.txt").getFile();

        List<File> fileList = Arrays.asList(
                new File(filePath1),
                new File(filePath2),
                new File(filePath3),
                new File(filePath4)
        );
        Set<String> resultSet = parallelReadFilter.parallelReadAndFilter(fileList, "r");
        assertNotNull(resultSet);
        assertEquals(true, resultSet.stream().allMatch(rs -> rs.startsWith("r")));
        assertEquals(7, resultSet.size());
    }
}
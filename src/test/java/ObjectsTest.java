import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;

import java.time.LocalDateTime;

public class ObjectsTest {

    static class Guava implements Comparable<Guava> {
        private final String manufacturer;
        private final String version;
        private final LocalDateTime releaseDate;

        public Guava(String manufacturer, String version, LocalDateTime releaseDate) {
            this.manufacturer = manufacturer;
            this.version = version;
            this.releaseDate = releaseDate;
        }


        public String getManufacturer() {
            return manufacturer;
        }

        public String getVersion() {
            return version;
        }

        public LocalDateTime getReleaseDate() {
            return releaseDate;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues().add("manufacturer", this.manufacturer)
                    .add("version", this.version)
                    .add("releaseDate", this.releaseDate).toString();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(manufacturer, version, releaseDate);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Guava guava = (Guava) obj;
            return Objects.equal(this.manufacturer, ((Guava) obj).manufacturer) &&
                    Objects.equal(this.version, ((Guava) obj).version) &&
                    Objects.equal(this.releaseDate, ((Guava) obj).releaseDate);
        }

        @Override
        public int compareTo(Guava o) {
            return ComparisonChain.start().compare(this.manufacturer, o.manufacturer)
                    .compare(this.releaseDate, o.releaseDate)
                    .compare(this.version, o.version).result();

        }


    }
    @Test
    public   void  testGauavaObj() {
        final  Guava guava=new Guava("Google","23.1",LocalDateTime.now());
        System.out.println(guava);
        System.out.println(guava.hashCode());
    }
}

package priceCompare.backend.stores.espak.service;

import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.enums.LocationName;

public enum EspakStoreLocation {
    TALLINN(LocationDto.builder()
            .locationName(LocationName.TALLINN)
            .address("Viadukti 42")
            .googleMapsLink("https://www.google.com/maps/place/Espak/@59.4162144,24.7583197,294m/data=!3m1!1e3!4m6!3m5!1s0x469294ba7f3eda07:0x60f6cbded3767bc6!8m2!3d59.4162858!4d24.7585894!16s%2Fg%2F1tctw6n_?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    KEILA(LocationDto.builder()
            .locationName(LocationName.KEILA)
            .address("Põhjakaare 3")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Keila/@59.312374,24.4265849,371m/data=!3m1!1e3!4m6!3m5!1s0x4692bd0aad341e77:0xc502b438079934!8m2!3d59.3123998!4d24.4274076!16s%2Fg%2F11h7q4n848?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TARTU(LocationDto.builder()
            .locationName(LocationName.TARTU)
            .address("Turu 24")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Tartu/@58.3688482,26.7428171,410m/data=!3m1!1e3!4m6!3m5!1s0x46eb3728326ad08d:0xd77e80d55cce1f7c!8m2!3d58.3689101!4d26.7430654!16s%2Fg%2F1tlzyw1s?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    PARNU(LocationDto.builder()
            .locationName(LocationName.PARNU)
            .address("Papiniidu 4")
            .googleMapsLink("https://www.google.com/maps/place/Espak+P%C3%A4rnu/@58.3720208,24.5506777,1862m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46ecfceaed057e8d:0x1ed6e45810b288da!8m2!3d58.3720194!4d24.5520065!16s%2Fg%2F1tjz6n1b?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    RAKVERE(LocationDto.builder()
            .locationName(LocationName.RAKVERE)
            .address("Rägavere 46")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Rakvere/@59.3397425,26.3790564,905m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4693767dff9368d7:0xdf32fd5cb6b83aeb!8m2!3d59.3397398!4d26.3816313!16s%2Fg%2F1tfq39l4?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    NARVA(LocationDto.builder()
            .locationName(LocationName.NARVA)
            .address("Rahu 36a")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Narva/@59.3917199,28.1698677,1761m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469437fc8c54f803:0x95e21dff4d6e942f!8m2!3d59.3917172!4d28.1724426!16s%2Fg%2F1thd0gsd?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    POLVA(LocationDto.builder()
            .locationName(LocationName.POLVA)
            .address("https://www.google.com/maps/place/Espak+P%C3%B5lva/@58.067032,27.0738238,1829m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eade47e344c537:0xe78541faf31981ef!8m2!3d58.0670292!4d27.0763987!16s%2Fg%2F1tgx0q8v?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    JOGEVA(LocationDto.builder()
            .locationName(LocationName.JOGEVA)
            .address("Tallinna mnt 2a")
            .googleMapsLink("https://www.google.com/maps/place/Espak+J%C3%B5geva/@58.7409285,26.3678493,929m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4694a904958ffcf9:0x14cf5908ec00d8cb!8m2!3d58.7409257!4d26.3704242!16s%2Fg%2F11cmt5f9sg?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    KURESSAARE(LocationDto.builder()
            .locationName(LocationName.KURESSAARE)
            .address("Uus-Roomassaare 35")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Kuressaare/@58.2414689,22.4908655,1852m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46f26d3d4a94b89b:0xfffe3a64b8d2ac23!8m2!3d58.2414661!4d22.4934404!16s%2Fg%2F1tksm12n?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    MUHU(LocationDto.builder()
            .locationName(LocationName.MUHU)
            .address("Piiri")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Muhu/@58.599866,23.1906466,933m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46f283c25da6898f:0xc3925d4a5d0db682!8m2!3d58.5998632!4d23.1932215!16s%2Fg%2F11qskngc07?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VILJANDI(LocationDto.builder()
            .locationName(LocationName.VILJANDI)
            .address("Reinu tee 35")
            .googleMapsLink("https://www.google.com/maps/place/Espak/@58.3516975,25.5678761,923m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46ec98f3fde4dc5b:0xf6ae572be74a2a35!8m2!3d58.3516947!4d25.570451!16s%2Fg%2F1tltl1gz?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    JOHVI(LocationDto.builder()
            .locationName(LocationName.JOHVI)
            .address("Lille 3")
            .googleMapsLink("https://www.google.com/maps/place/Espak+J%C3%B5hvi/@59.3582465,27.4208444,905m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46946f80b4e202b9:0xf67f6f0e7259e593!8m2!3d59.3582438!4d27.4234193!16s%2Fg%2F11cmt51bm2?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    RAPLA(LocationDto.builder()
            .locationName(LocationName.RAPLA)
            .address("Linda 1")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Rapla/@59.0101815,24.7954399,1781m/data=!3m2!1e3!4b1!4m6!3m5!1s0x4692d210c18acc29:0xa03bd6937e2fffbc!8m2!3d59.0101788!4d24.7980148!16s%2Fg%2F1tdy33z2?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    PAIDE(LocationDto.builder()
            .locationName(LocationName.PAIDE)
            .address("Prääma tee 20")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Paide/@58.8901679,25.5363186,901m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469336a3172fd823:0xd6c0797aeefa92d3!8m2!3d58.8901652!4d25.5388935!16s%2Fg%2F1tpb9n2p?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    TURI(LocationDto.builder()
            .locationName(LocationName.TURI)
            .address("Kaare 35c")
            .googleMapsLink("https://www.google.com/maps/place/Espak+T%C3%BCri/@58.8120977,25.3990294,1807m/data=!3m2!1e3!4b1!4m6!3m5!1s0x469333525da6ad9f:0x5f0ad8f15e5d5e41!8m2!3d58.8120949!4d25.4016043!16s%2Fg%2F1tcy7k64?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VORU(LocationDto.builder()
            .locationName(LocationName.VORU)
            .address("Kubja tee 36")
            .googleMapsLink("https://www.google.com/maps/place/ESPAK+Empak+V%C3%B5ru+V%C3%B5ru-AS/@57.8218331,26.9976834,937m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eafaf5ca92b18f:0xcfc4d7b777f1d74f!8m2!3d57.8218303!4d27.0002583!16s%2Fg%2F11b6jg___2?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    RAPINA(LocationDto.builder()
            .locationName(LocationName.RAPINA)
            .address("Pargi 7a")
            .googleMapsLink("https://www.google.com/maps/place/ESPAK+R%C3%84PINA+V%C3%B5ru+AS+Empak/@58.0973615,27.4577942,1910m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eac4f87f9a915d:0xc9cfb75df41a94e6!8m2!3d58.0973587!4d27.4603691!16s%2Fg%2F11cly8jv2z?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build()),
    VASTSELIINA(LocationDto.builder()
            .locationName(LocationName.VASTSELIINA)
            .address("Võidu 3a")
            .googleMapsLink("https://www.google.com/maps/place/Espak+Vastseliina/@57.7326799,27.2746073,1912m/data=!3m2!1e3!4b1!4m6!3m5!1s0x46eaf3fe17029a8d:0x3b1783958bae6711!8m2!3d57.7326771!4d27.2771822!16s%2Fg%2F11lfbjjlr5?entry=ttu&g_ep=EgoyMDI0MTEyNC4xIKXMDSoASAFQAw%3D%3D")
            .build());

    protected final LocationDto location;

    EspakStoreLocation(LocationDto location) {
        this.location = location;
    }
}

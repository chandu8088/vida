import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import appUtils from "../../../../site/scripts/utils/appUtils";
import ProfileDetails from "../ProfileDetails/ProfileDetails";
import breakpoints from "../../../../site/scripts/media-breakpoints";
import {
  useUserData,
  useElligibleAddressUpdate
} from "../../../hooks/userProfile/userProfileHooks";
import { connect } from "react-redux";
import loginUtils from "../../../../site/scripts/utils/loginUtils";
import ProfileImageUpload from "../ProfileImageUpload/ProfileImageUpload";
import Logout from "../Logout/Logout";
import analyticsUtils from "../../../../site/scripts/utils/analyticsUtils";
import { useAllUserTestRides } from "../../../hooks/userProfile/userProfileHooks";
import CONSTANT from "../../../../site/scripts/constant";

const UserProfile = (props) => {
  const { config, userProfileProps } = props;
  const { profile_pic, allUserTestRidesData } = userProfileProps;
  const {
    testRideCardConfig,
    buyNowCardConfig,
    orderCardConfig,
    profilePicConfig,
    firstNameField,
    lastNameField,
    phoneNumberField,
    emailField,
    pinCodeField,
    datalayerConfig
  } = config;
  const isLoggedIn = loginUtils.isSessionActive();
  const [fileName, setFileName] = useState();
  const [isEligible, setIsEligible] = useState();
  const [showImageCropper, setShowImageCropper] = useState(false);
  const isDesktop = window.matchMedia(
    breakpoints.mediaExpression.desktop
  ).matches;
  const isTab = window.matchMedia(breakpoints.mediaExpression.tablet).matches;

  const getUserData = useUserData();
  const getAllTestRides = useAllUserTestRides();
  const getElligibleAddressUpdate = useElligibleAddressUpdate();

  const isAnalyticsEnabled = analyticsUtils.isAnalyticsEnabled();

  const checkEligibleForTestRide = async () => {
    if (allUserTestRidesData.items && allUserTestRidesData.items.length > 0) {
      const shortTestRideData = allUserTestRidesData?.items.filter(
        (item) => !item.IsLTTR
      );

      const confirmedData = shortTestRideData.filter(
        (item) => item.dmpl__Status__c === CONSTANT.TEST_RIDE_STATUS.CONFIRMED
      );

      if (confirmedData.length > 0) {
        window.location.href = orderCardConfig.orderNavLink + "?" + "testride";
      } else {
        window.location.href = testRideCardConfig.testRideNavLink;
      }
    } else {
      window.location.href = testRideCardConfig.testRideNavLink;
    }
  };

  const ctaTracking = (e, content) => {
    if (isAnalyticsEnabled) {
      const customLink = {
        ctaText: text || e?.target?.alt || e?.target?.innerText,
        ctaLocation:
          e?.target?.dataset?.linkPosition ||
          e?.target?.closest("a")?.dataset?.linkPosition
      };
      analyticsUtils.trackCTAClicksVida2(customLink, "ctaButtonClick");
    }
    if (content.isTestRideUrl) {
      checkEligibleForTestRide();
    }
  };

  const checkEligibleForAddress = async () => {
    const eligibleResult = await getElligibleAddressUpdate();

    if (
      eligibleResult &&
      eligibleResult?.data &&
      eligibleResult.data.customer
    ) {
      setIsEligible(eligibleResult.data.customer.isEligibleForAddressUpdate);
    }
  };

  useEffect(() => {
    getUserData();
    getAllTestRides();
    checkEligibleForAddress();
  }, []);

  const handleFileName = (fileName) => {
    setFileName(fileName);
  };

  return (
    <div className="user-profile">
      {!isDesktop && !isTab && (
        <div className="user-profile-logout-link">
          <Logout />
        </div>
      )}
      <div className="vida-user-order-details">
        {!profile_pic && (
          <div className="user-image-card">
            <h3>{profilePicConfig?.uploadPicText}</h3>
            <div className="image-upload">
              <label htmlFor="profile-image">
                <img
                  src={`${appUtils.getConfig(
                    "resourcePath"
                  )}images/svg/plus-circle.svg`}
                  loading="lazy"
                />
              </label>
              <ProfileImageUpload
                handleFileName={handleFileName}
                config={config}
                id="profile-image"
                fileName={fileName}
              />
            </div>
          </div>
        )}
        <a
          className="user-test-ride-card-link"
          onClick={checkEligibleForTestRide}
        >
          <div className="user-test-ride-card">
            <div className="user-test-ride-card__image">
              <img
                src={testRideCardConfig.testRideScooterImage}
                alt={testRideCardConfig?.testRideScooterImgAlt}
                title={testRideCardConfig?.testRideScooterImgTitle}
                loading="lazy"
              />
            </div>

            <div className="user-test-ride-card__details">
              <h4 className="user-test-ride__label">
                {testRideCardConfig?.testRideHeader}
              </h4>
              <p className="user-test-ride__value">
                {testRideCardConfig?.testRideDescription}
              </p>
            </div>
            <div className="user-test-ride-card__action">
              <a
                data-link-position={
                  datalayerConfig?.dataPosition || "userProfile"
                }
                onClick={(e) => ctaTracking(e, testRideCardConfig)}
              >
                <img
                  src={`${appUtils.getConfig(
                    "resourcePath"
                  )}images/svg/solar_square-arrow-right-up-bold-duotone.svg`}
                  loading="lazy"
                ></img>
              </a>
            </div>
          </div>
        </a>
        <a
          className="user-buy-now-card-link"
          href={buyNowCardConfig.buyNowNavLink}
        >
          <div className="user-buy-now-card">
            <div className="user-test-ride-card__image">
              <img
                src={buyNowCardConfig.buyNowCardScooterImage}
                alt={buyNowCardConfig?.buyNowScooterImgAlt}
                title={buyNowCardConfig?.buyNowScooterImgTitle}
                loading="lazy"
              />
            </div>

            <div className="user-test-ride-card__details">
              <h4 className="user-test-ride__label">
                {buyNowCardConfig?.buyNowCardHeader}
              </h4>
              <p className="user-test-ride__value">
                {buyNowCardConfig?.buyNowCardDescription}
              </p>
            </div>
            <div className="user-test-ride-card__action">
              <a
                data-link-position={
                  datalayerConfig?.dataPosition || "userProfile"
                }
                onClick={(e) =>
                  ctaTracking(e, buyNowCardConfig?.buyNowCardHeader)
                }
                href={buyNowCardConfig.buyNowNavLink}
              >
                <img
                  src={`${appUtils.getConfig(
                    "resourcePath"
                  )}images/svg/solar_square-arrow-right-up-bold-duotone.svg`}
                  loading="lazy"
                ></img>
              </a>
            </div>
          </div>
        </a>
        <a className="user-order-card-link" href={orderCardConfig.orderNavLink}>
          <div className="user-order-card">
            <div className="user-test-ride-card__image">
              <img
                src={orderCardConfig.orderCardScooterImage}
                alt={orderCardConfig?.orderCardScooterImgAlt}
                title={orderCardConfig?.orderCardScooterImgTitle}
                loading="lazy"
              />
            </div>

            <div className="user-test-ride-card__details">
              <h4 className="user-test-ride__label">
                {orderCardConfig?.orderCardHeader}
              </h4>
              <p className="user-test-ride__value">
                {orderCardConfig?.orderCardDescription}
              </p>
            </div>
            <div className="user-test-ride-card__action">
              <a
                data-link-position={
                  datalayerConfig?.dataPosition || "userProfile"
                }
                onClick={(e) =>
                  ctaTracking(e, orderCardConfig?.orderCardHeader)
                }
                href={orderCardConfig.orderNavLink}
              >
                <img
                  src={`${appUtils.getConfig(
                    "resourcePath"
                  )}images/svg/solar_square-arrow-right-up-bold-duotone.svg`}
                  loading="lazy"
                ></img>
              </a>
            </div>
          </div>
        </a>
      </div>
      <div className="vida-user-details">
        <ProfileDetails
          firstNameField={firstNameField}
          lastNameField={lastNameField}
          emailField={emailField}
          phoneNumberField={phoneNumberField}
          pinCodeField={pinCodeField}
          profilePicConfig={profilePicConfig}
          config={config}
          eligibleForAddressUpdate={isEligible}
        />
      </div>
    </div>
  );
};

const mapStateToProps = ({ userProfileDataReducer, userTestRideReducer }) => {
  return {
    userProfileProps: {
      profile_pic: userProfileDataReducer.profile_pic,
      allUserTestRidesData: {
        items: userTestRideReducer.items
      }
    }
  };
};

UserProfile.propTypes = {
  config: PropTypes.object,
  userProfileProps: PropTypes.shape({
    profile_pic: PropTypes.string,
    allUserTestRidesData: PropTypes.shape({
      items: PropTypes.array
    })
  })
};

export default connect(mapStateToProps)(UserProfile);
